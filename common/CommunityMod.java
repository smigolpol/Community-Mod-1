package communityMod.common;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.Configuration;
import communityMod.client.ClientProxyCommunityMod;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid = "Community_Mod", name = "Community_Mod", version = "1.0")
@NetworkMod(clientSideRequired = true, serverSideRequired = false)

public class CommunityMod {

	@SidedProxy(clientSide = "communityMod.client.ClientProxyCommunityMod",
				serverSide = "communityMod.common.CommonProxyCommunityMod")
	public static ClientProxyCommunityMod proxy = new ClientProxyCommunityMod();
	
	public static Block siliconOre;
	public static Block leadOre;
	public static Block titaniumOre;
	public static Block leadBlock;
	public static Block titaniumBlock;
	
	public static int siliconOreID;
	public static int leadOreID;
	public static int titaniumOreID;
	public static int leadBlockID;
	public static int titaniumBlockID;
	
	@PreInit
	public void preInit(FMLPreInitializationEvent event){
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());
		config.load();
		
		siliconOreID = config.get("BlockIDs", "Silicon Ore ID", 700).getInt();
		leadOreID = config.get("Block IDs", "Lead Ore ID", 701).getInt();
		titaniumOreID = config.get("BlockIDs", "Titanium Ore ID", 702).getInt();
		leadBlockID = config.get("BlockIDs", "Lead Block ID", 703).getInt();
		titaniumBlockID = config.get("BlockIDs", "Titanium Block ID", 704).getInt();
		
		config.save();
	}
	
	@Init
	public void load(FMLInitializationEvent event){
		proxy.registerRenders();
		
		siliconOre = new BlockOre(siliconOreID, 0).setHardness(10F).setResistance(.2F).setBlockName("SiliconOre").setCreativeTab(CreativeTabs.tabBlock);
		leadOre = new BlockOre(leadOreID, 1).setHardness(10F).setResistance(0.2F).setBlockName("LeadOre").setCreativeTab(CreativeTabs.tabBlock);
		titaniumOre = new BlockOre(titaniumOreID, 2).setHardness(10F).setResistance(0.2F).setBlockName("TitaniumOre").setCreativeTab(CreativeTabs.tabBlock);
		leadBlock = new BlockMetal(leadBlockID, 4).setHardness(12F).setResistance(10F).setBlockName("LeadBlock").setCreativeTab(CreativeTabs.tabBlock);
		titaniumBlock = new BlockMetal(titaniumBlockID, 3).setHardness(12F).setResistance(10F).setBlockName("TitaniumBlock").setCreativeTab(CreativeTabs.tabBlock);
		
		gameRegisters();
		languageRegisters();
		craftingRecipes();
	}
	
	private static void craftingRecipes(){
		
	}
	
	private static void gameRegisters(){
		GameRegistry.registerBlock(siliconOre, "SiliconOre");
		GameRegistry.registerBlock(leadOre, "LeadOre");
		GameRegistry.registerBlock(titaniumOre, "TitaniumOre");
		GameRegistry.registerBlock(leadBlock, "LeadBlock");
		GameRegistry.registerBlock(titaniumBlock, "TitaniumBlock");
	}
	
	private static void languageRegisters(){
		LanguageRegistry.addName(siliconOre, "Silicon Ore");
		LanguageRegistry.addName(leadOre, "Lead Ore");
		LanguageRegistry.addName(titaniumOre, "Titanium Ore");
		LanguageRegistry.addName(leadBlock, "Lead Block");
		LanguageRegistry.addName(titaniumBlock, "Titanium Block");
	}
}
