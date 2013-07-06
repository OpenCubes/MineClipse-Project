
package net.minecraft.null;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;

@Mod(modid="mod_1373043643181", name="mod_1373043643181", version="1373043643181")
@NetworkMod(clientSideRequired=true, serverSideRequired=false)
public class Mod1373043643181 { 

        // The instance of your mod that Forge uses.
        @Instance("1373043643181")
        public static Generic instance;
       
        // Says where the client and server 'proxy' code is loaded.
        @SidedProxy(clientSide="mod_1373043643181.client.ClientProxy", serverSide="mod_1373043643181.CommonProxy")
        public static CommonProxy proxy;
        
       
        @EventHandler
        public void preInit(FMLPreInitializationEvent event) {
                // Stub Method
        }
       
        @EventHandler
        public void load(FMLInitializationEvent event) {
                proxy.registerRenderers();
        }
       
        @EventHandler
        public void postInit(FMLPostInitializationEvent event) {
                // Stub Method
        }
}