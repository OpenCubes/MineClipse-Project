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
}
