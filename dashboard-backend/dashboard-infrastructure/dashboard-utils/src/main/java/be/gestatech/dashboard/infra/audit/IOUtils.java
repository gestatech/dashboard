package be.gestatech.dashboard.infra.audit;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by amurifa on 30/06/2017.
 */
public class IOUtils {

	public static final int EOF = -1;

	private static final int DEFAULT_BUFFER_SIZE = 1024 * 4;

	private IOUtils() {
		throw new RuntimeException();
	}

	public static byte[] toByteArray(final InputStream input) throws IOException {
		try (final ByteArrayOutputStream output = new ByteArrayOutputStream()) {
			copy(input, output);
			return output.toByteArray();
		}
	}

	public static int copy(final InputStream input, final OutputStream output) throws IOException {
		final long count = copyLarge(input, output);
		if (count > Integer.MAX_VALUE) {
			return -1;
		}
		return (int) count;
	}

	public static long copy(final InputStream input, final OutputStream output, final int bufferSize)
			throws IOException {
		return copyLarge(input, output, new byte[bufferSize]);
	}

	public static long copyLarge(final InputStream input, final OutputStream output)
			throws IOException {
		return copy(input, output, DEFAULT_BUFFER_SIZE);
	}

	public static long copyLarge(final InputStream input, final OutputStream output, final byte[] buffer)
			throws IOException {
		long count = 0;
		int n;
		while (EOF != (n = input.read(buffer))) {
			output.write(buffer, 0, n);
			count += n;
		}
		return count;
	}

}
