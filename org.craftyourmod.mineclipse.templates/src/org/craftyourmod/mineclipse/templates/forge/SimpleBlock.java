package org.craftyourmod.mineclipse.templates.forge;

import java.util.HashMap;

public class SimpleBlock
{
  protected static String nl;
  public static synchronized SimpleBlock create(String lineSeparator)
  {
    nl = lineSeparator;
    SimpleBlock result = new SimpleBlock();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = NL + "package ";
  protected final String TEXT_3 = ";" + NL + "" + NL + "import net.minecraft.block.Block;" + NL + "import net.minecraft.block.material.Material;" + NL + "import net.minecraft.client.renderer.texture.IconRegister;" + NL + "" + NL + "public class Block";
  protected final String TEXT_4 = " extends Block" + NL + "{" + NL + "" + NL + "        public GenericBlock (int id, Material material)" + NL + "        {" + NL + "                super(id, material);" + NL + "        }" + NL + "       " + NL + "        @Override" + NL + "        @SideOnly(Side.CLIENT)" + NL + "        public void registerIcons(IconRegister iconRegister) {" + NL + "            this.blockIcon = iconRegister.registerIcon(\"Generic:\" + (this.getUnlocalizedName().substring(5)));" + NL + "        }" + NL + "" + NL + "}";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append(TEXT_1);
    HashMap map = (HashMap) argument;
    stringBuffer.append(TEXT_2);
    stringBuffer.append(map.get("BlockPackage"));
    stringBuffer.append(TEXT_3);
    stringBuffer.append(map.get("BlockClassName"));
    stringBuffer.append(TEXT_4);
    return stringBuffer.toString();
  }
}
