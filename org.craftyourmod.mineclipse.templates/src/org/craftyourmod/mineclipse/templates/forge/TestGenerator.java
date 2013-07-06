package org.craftyourmod.mineclipse.templates.forge;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class TestGenerator {

	public static void main(final String[] args) {
		File file = new File("generated");
		String name = "" + Calendar.getInstance().getTime().getTime();
		File dir = new File(file, "gen#" + name);
		dir.mkdirs();
		Map<String, String> map = new HashMap<String, String>();
		map.put("ModVersion", name);
		map.put("ModName", "mod_" + name);
		map.put("ModId", "mod_" + name);
		map.put("ClassName", "Mod" + name);
		map.put("ClientProxy", "CLIENT_PROXY");
		map.put("CommonProxy", "CMN_PROXY");
		map.put("Package", "net.minecraft.tmplt.test");
		String s = new Mod().generate(map);
		try {
			FileWriter fw = new FileWriter(
					new File(dir, "Mod" + name + ".java"));
			fw.write(s);
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
