package org.craftyourmod.mineclipse.templates.forge;

import java.util.HashMap;

public class Mod
{
  protected static String nl;
  public static synchronized Mod create(String lineSeparator)
  {
    nl = lineSeparator;
    Mod result = new Mod();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = NL + "package net.minecraft.";
  protected final String TEXT_3 = ";" + NL + "" + NL + "import cpw.mods.fml.common.Mod;" + NL + "import cpw.mods.fml.common.Mod.Init;" + NL + "import cpw.mods.fml.common.Mod.Instance;" + NL + "import cpw.mods.fml.common.Mod.PostInit;" + NL + "import cpw.mods.fml.common.Mod.PreInit;" + NL + "import cpw.mods.fml.common.SidedProxy;" + NL + "import cpw.mods.fml.common.event.FMLInitializationEvent;" + NL + "import cpw.mods.fml.common.event.FMLPostInitializationEvent;" + NL + "import cpw.mods.fml.common.event.FMLPreInitializationEvent;" + NL + "import cpw.mods.fml.common.network.NetworkMod;" + NL + "" + NL + "@Mod(modid=\"";
  protected final String TEXT_4 = "\", name=\"";
  protected final String TEXT_5 = "\", version=\"";
  protected final String TEXT_6 = "\")" + NL + "@NetworkMod(clientSideRequired=true, serverSideRequired=false)" + NL + "public class Generic { " + NL + "" + NL + "        // The instance of your mod that Forge uses." + NL + "        @Instance(\"";
  protected final String TEXT_7 = "\")" + NL + "        public static Generic instance;" + NL + "       " + NL + "        // Says where the client and server 'proxy' code is loaded." + NL + "        @SidedProxy(clientSide=\"";
  protected final String TEXT_8 = ".client.ClientProxy\", serverSide=\"";
  protected final String TEXT_9 = ".CommonProxy\")" + NL + "        public static CommonProxy proxy;" + NL + "        " + NL + "       " + NL + "        @EventHandler" + NL + "        public void preInit(FMLPreInitializationEvent event) {" + NL + "                // Stub Method" + NL + "        }" + NL + "       " + NL + "        @EventHandler" + NL + "        public void load(FMLInitializationEvent event) {" + NL + "                proxy.registerRenderers();" + NL + "        }" + NL + "       " + NL + "        @EventHandler" + NL + "        public void postInit(FMLPostInitializationEvent event) {" + NL + "                // Stub Method" + NL + "        }" + NL + "}";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append(TEXT_1);
    HashMap map = (HashMap) argument;
    stringBuffer.append(TEXT_2);
    stringBuffer.append(map.get("Package"));
    stringBuffer.append(TEXT_3);
    stringBuffer.append(map.get("ModName"));
    stringBuffer.append(TEXT_4);
    stringBuffer.append(map.get("ModName"));
    stringBuffer.append(TEXT_5);
    stringBuffer.append(map.get("ModVersion"));
    stringBuffer.append(TEXT_6);
    stringBuffer.append(map.get("ModVersion"));
    stringBuffer.append(TEXT_7);
    stringBuffer.append(map.get("ModName"));
    stringBuffer.append(TEXT_8);
    stringBuffer.append(map.get("ModName"));
    stringBuffer.append(TEXT_9);
    return stringBuffer.toString();
  }
}
