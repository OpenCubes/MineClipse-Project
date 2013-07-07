package org.craftyourmod.mineclipse.core;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Util {

	public static Object getKeyFromObject(final Map map, final Object obj) {
		for (Object key : map.keySet()) {
			Object o = map.get(key);
			if (o.equals(obj))
				return key;
		}
		return null;
	}

	public static void get(final URL url, final File target) throws IOException {
		ReadableByteChannel rbc = Channels.newChannel(url.openStream());
		FileOutputStream fos = new FileOutputStream(target);
		fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
		fos.close();
	}

	public static void deleteFolder(final File folder) {
		File[] files = folder.listFiles();
		if (files != null)
			for (File f : files)
				if (f.isDirectory())
					deleteFolder(f);
				else
					f.delete();
		folder.delete();
	}

	public static File getMinecraftWokingDirectory() {
		return new File(System.getenv("APPDATA"), "/.minecraft/");

	}

	public static String computeDescription(final Throwable e) {
		return e.getClass().getName()
				+ ((e.getLocalizedMessage() != null) ? (": " + e
						.getLocalizedMessage()) : (e.getCause() != null) ? e
						.getCause().getLocalizedMessage() != null ? ": "
						+ e.getCause().getLocalizedMessage() : "" : "");
	}

	/**
	 * Unzip it
	 * 
	 * @param zipFile
	 *            input zip file
	 * @param output
	 *            zip file output folder
	 * @throws IOException
	 */
	public static void unZipIt(final String zipFile, final String outputFolder)
			throws IOException {

		byte[] buffer = new byte[1024];

		// create output directory is not exists
		File folder = new File(outputFolder);
		if (!folder.exists())
			folder.mkdir();

		// get the zip file content
		ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFile));
		// get the zipped file list entry
		ZipEntry ze = zis.getNextEntry();

		while (ze != null) {

			String fileName = ze.getName();
			File newFile = new File(outputFolder + File.separator + fileName);

			System.out.println("file unzip : " + newFile.getAbsoluteFile());

			// create all non exists folders
			// else you will hit FileNotFoundException for compressed folder
			new File(newFile.getParent()).mkdirs();

			FileOutputStream fos = new FileOutputStream(newFile);

			int len;
			while ((len = zis.read(buffer)) > 0)
				fos.write(buffer, 0, len);

			fos.close();
			ze = zis.getNextEntry();
		}

		zis.closeEntry();
		zis.close();

		System.out.println("Done");

	}

	/**
	 * Removes a pause instruction in a bat file
	 * 
	 * @param exec
	 */
	public static void removePause(final File f) {

		BufferedReader br = null;
		Writer fw;
		BufferedWriter bw = null;

		try {

			String sCurrentLine;
			String finalS = "";
			br = new BufferedReader(new FileReader(f));

			while ((sCurrentLine = br.readLine()) != null)
				finalS += sCurrentLine + "\n";

			File file = new File(f.getAbsolutePath());

			// if file doesnt exists, then create it
			if (!file.exists())
				file.createNewFile();

			fw = new FileWriter(file.getAbsoluteFile());
			bw = new BufferedWriter(fw);
			bw.write(finalS.replaceAll("Pause", ""));

		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			try {
				if (br != null)
					br.close();
				if (bw != null)
					bw.close();
			} catch (IOException ex) {
				ex.printStackTrace();
				throw new RuntimeException(ex);
			}
		}
	}

	private final static String NON_THIN = "[^iIl1\\.,']";

	private static int textWidth(final String str) {
		return str.length() - (str.replaceAll(NON_THIN, "").length() / 2);
	}

	/**
	 * Abbreviates a String using ellipses. This will turn
	 * "Now is the time for all good men" into "Now is the time for..."
	 * 
	 * Specifically:
	 * <ul>
	 * <li>If <code>str</code> is less than <code>maxWidth</code> characters
	 * long, return it.</li>
	 * <li>Else abbreviate it to <code>(substring(str, 0, max-3) + "...")</code>
	 * .</li>
	 * <li>If <code>maxWidth</code> is less than <code>4</code>, throw an
	 * <code>IllegalArgumentException</code>.</li>
	 * <li>In no case will it return a String of length greater than
	 * <code>maxWidth</code>.</li>
	 * </ul>
	 * 
	 * 
	 * <pre>
	 * StringUtils.abbreviate(null, *)      = null
	 * StringUtils.abbreviate("", 4)        = ""
	 * StringUtils.abbreviate("abcdefg", 6) = "abc..."
	 * StringUtils.abbreviate("abcdefg", 7) = "abcdefg"
	 * StringUtils.abbreviate("abcdefg", 8) = "abcdefg"
	 * StringUtils.abbreviate("abcdefg", 4) = "a..."
	 * StringUtils.abbreviate("abcdefg", 3) = IllegalArgumentException
	 * </pre>
	 * 
	 * @param str
	 *            the String to check, may be null
	 * @param maxWidth
	 *            maximum length of result String, must be at least 4
	 * @return abbreviated String, <code>null</code> if null String input
	 * @throws IllegalArgumentException
	 *             if the width is too small
	 * @since 2.0
	 */
	public static String abbreviate(final String str, final int maxWidth) {
		return abbreviate(str, 0, maxWidth);
	}

	/**
	 * Abbreviates a String using ellipses. This will turn
	 * "Now is the time for all good men" into "...is the time for..."
	 * 
	 * Works like <code>abbreviate(String, int)</code>, but allows you to
	 * specify a "left edge" offset. Note that this left edge is not necessarily
	 * going to be the leftmost character in the result, or the first character
	 * following the ellipses, but it will appear somewhere in the result.
	 * 
	 * In no case will it return a String of length greater than
	 * <code>maxWidth</code>.
	 * 
	 * <pre>
	 * StringUtils.abbreviate(null, *, *)                = null
	 * StringUtils.abbreviate("", 0, 4)                  = ""
	 * StringUtils.abbreviate("abcdefghijklmno", -1, 10) = "abcdefg..."
	 * StringUtils.abbreviate("abcdefghijklmno", 0, 10)  = "abcdefg..."
	 * StringUtils.abbreviate("abcdefghijklmno", 1, 10)  = "abcdefg..."
	 * StringUtils.abbreviate("abcdefghijklmno", 4, 10)  = "abcdefg..."
	 * StringUtils.abbreviate("abcdefghijklmno", 5, 10)  = "...fghi..."
	 * StringUtils.abbreviate("abcdefghijklmno", 6, 10)  = "...ghij..."
	 * StringUtils.abbreviate("abcdefghijklmno", 8, 10)  = "...ijklmno"
	 * StringUtils.abbreviate("abcdefghijklmno", 10, 10) = "...ijklmno"
	 * StringUtils.abbreviate("abcdefghijklmno", 12, 10) = "...ijklmno"
	 * StringUtils.abbreviate("abcdefghij", 0, 3)        = IllegalArgumentException
	 * StringUtils.abbreviate("abcdefghij", 5, 6)        = IllegalArgumentException
	 * </pre>
	 * 
	 * @param str
	 *            the String to check, may be null
	 * @param offset
	 *            left edge of source String
	 * @param maxWidth
	 *            maximum length of result String, must be at least 4
	 * @return abbreviated String, <code>null</code> if null String input
	 * @throws IllegalArgumentException
	 *             if the width is too small
	 * @since 2.0
	 */
	public static String abbreviate(final String str, int offset,
			final int maxWidth) {
		if (str == null)
			return null;
		if (maxWidth < 4)
			throw new IllegalArgumentException(
					"Minimum abbreviation width is 4");
		if (str.length() <= maxWidth)
			return str;
		if (offset > str.length())
			offset = str.length();
		if ((str.length() - offset) < (maxWidth - 3))
			offset = str.length() - (maxWidth - 3);
		if (offset <= 4)
			return str.substring(0, maxWidth - 3) + "...";
		if (maxWidth < 7)
			throw new IllegalArgumentException(
					"Minimum abbreviation width with offset is 7");
		if ((offset + (maxWidth - 3)) < str.length())
			return "..." + abbreviate(str.substring(offset), maxWidth - 3);
		return "..." + str.substring(str.length() - (maxWidth - 3));
	}

}
