package org.craftyourmod.mineclipse.core;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.Map;

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

}
