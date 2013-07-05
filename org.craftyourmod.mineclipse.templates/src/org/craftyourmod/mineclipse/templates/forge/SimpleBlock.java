package org.craftyourmod.mineclipse.templates.forge;

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
  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    return stringBuffer.toString();
  }
}
