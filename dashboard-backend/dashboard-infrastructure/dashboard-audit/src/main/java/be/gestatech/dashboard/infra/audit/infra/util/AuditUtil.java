package be.gestatech.dashboard.infra.audit.infra.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.persistence.Id;
import javax.persistence.Transient;

import be.gestatech.dashboard.infra.audit.entity.AuditInitialValues;
import be.gestatech.dashboard.infra.audit.entity.api.AuditReadable;
import be.gestatech.dashboard.infra.audit.infra.constant.AuditAction;

/**
 * Created by amurifa on 13/07/2017.
 */
public final class AuditUtil {

	private static final Logger LOGGER = Logger.getLogger(AuditUtil.class.getName());

	private AuditUtil() {}

	public static String getEntityName(AuditInitialValues entity) {
		return getEntityName(entity.getClass());
	}

	public static String getEntityName(Class<?> entityClass) {
		if (entityClass.isAnnotationPresent(AuditName.class)) {
			return entityClass.getAnnotation(AuditName.class).value();
		}
		return entityClass.getSimpleName();
	}

	public static List<Field> getFields(Class<?> targetClass) {

		List<Field> fields = new ArrayList<>();
		List<String> ignoreFields = new ArrayList<>();
		Class<?> inheritanceClass = targetClass;

		while (inheritanceClass != null) {
			if (inheritanceClass.isAnnotationPresent(AuditIgnoreFields.class)) {
				ignoreFields.addAll(Arrays.asList(inheritanceClass.getAnnotation(AuditIgnoreFields.class).value()));
			}
			for (Field field : inheritanceClass.getDeclaredFields()) {
				// There is no need to check the JPA identifier and transient fields are a irrelevant
				if (!field.isAnnotationPresent(Id.class) && !field.isAnnotationPresent(Transient.class)
						// Defined to ignore
						&& !field.isAnnotationPresent(AuditIgnoreField.class) && !ignoreFields.contains(field.getName())
						// Ignore collections, final and static fields
						&& !Collection.class.isAssignableFrom(field.getType()) && !Modifier.isFinal(field.getModifiers())
						&& !Modifier.isStatic(field.getModifiers())) {
					fields.add(field);
				}
			}
			inheritanceClass = inheritanceClass.getSuperclass();
		}
		return fields;
	}

	public static boolean groupEvents(AuditInitialValues entity, AuditAction action) {
		return (AuditAction.CREATE.equals(action) || AuditAction.UPDATE.equals(action)) && entity.getClass().isAnnotationPresent(AuditGroupEvents.class);
	}

	public static String getFieldName(Field field) {
		if (field.isAnnotationPresent(AuditName.class)) {
			return field.getAnnotation(AuditName.class).value();
		}
		return field.getName();
	}

	public static String getClassName(Class<?> clazz) {
		if (clazz.isAnnotationPresent(AuditName.class)) {
			return clazz.getAnnotation(AuditName.class).value();
		}
		return clazz.getSimpleName();
	}

	public static Map<String, Field> getNameFieldMapping(AuditInitialValues entity) {
		return getFields(entity.getClass()).stream().collect(Collectors.toMap(f -> getFieldName(f), f -> f));
	}

	public static Map<String, Object> getValues(AuditInitialValues entity) {
		Map<String, Object> map = new HashMap<>();
		for (Field field : AuditUtil.getFields(entity.getClass())) {
			try {
				if (field.isAnnotationPresent(AuditCollectionParent.class)) {
					continue;
				}
				field.setAccessible(true);
				Object value = field.get(entity);
				map.put(getFieldName(field), processFieldValue(value, field));
				if (field.isAnnotationPresent(AuditDeleteMarker.class) && value != null) {
					map.put(AuditDeleteMarker.DELETE_MARKER, value);
				}
			} catch (IllegalArgumentException | IllegalAccessException e) {
				// Does nothing in this case
			}
		}
		return map;
	}

	private static Object processFieldValue(Object value, Field field) {

		if (field.getType().isEnum() && value instanceof AuditReadable) {
			return ((AuditReadable) value).toAuditableString();
		}
		if (Objects.nonNull(value) && field.isAnnotationPresent(AuditDateTimePattern.class)) {
			String datePattern = field.getAnnotation(AuditDateTimePattern.class).value();
			if (value instanceof LocalDateTime) {
				return ((LocalDateTime) value).format(DateTimeFormatter.ofPattern(datePattern));
			}
			if (value instanceof Date) {
				return new SimpleDateFormat(datePattern).format(((Date) value));
			}
		}
		if (Objects.nonNull(value) && field.isAnnotationPresent(AuditBooleanLabels.class) && value instanceof Boolean) {
			if ((boolean) value) {
				return field.getAnnotation(AuditBooleanLabels.class).trueLabel();
			}
			return field.getAnnotation(AuditBooleanLabels.class).falseLabel();
		}
		return value;
	}

	public static Map<Class<?>, Long> getParentReferences(AuditInitialValues entity) {

		Map<Class<?>, Long> parents = new HashMap<>();

		try {
			for (Field field : getFields(entity.getClass())) {
				if (field.isAnnotationPresent(AuditCollectionParent.class)) {
					field.setAccessible(true);
					Object fieldValue = field.get(entity);
					for (Field parentField : getFields(field.getType())) {
						if (parentField.isAnnotationPresent(Id.class)) {
							parentField.setAccessible(true);
							Object id = parentField.get(fieldValue);
							if (Objects.nonNull(id) && (parentField.getType().equals(Long.class) || parentField.getType().equals(long.class))) {
								parents.put(field.getType(), (Long) id);
							}
						}
					}
				}
			}
		} catch (SecurityException | IllegalArgumentException | IllegalAccessException e) {
			LOGGER.warning(() -> String.format("Could not get parent references: {%s}", e));
		}
		return parents;
	}

}
