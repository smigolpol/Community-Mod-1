package communityMod.common.blocks;

import java.util.Random;

import net.minecraft.block.BlockFlowing;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import net.minecraftforge.liquids.ILiquid;

import communityMod.common.CommunityMod;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockLiquidConcreteFlowing extends BlockFlowing implements ILiquid {

    private String textureName;
    private String sideTextureName;
    private Icon side;
    private Icon bottom;
    private int freezeProgress = 0;

    protected BlockLiquidConcreteFlowing(int par1, String textureName) {
        super(par1, Material.water);

        this.blockHardness = 100.0F;
        this.setLightOpacity(2);
        this.setCreativeTab(CommunityMod.modTab);
        this.textureName = textureName;
        sideTextureName = "LiquidConcrete";
    }

    @Override
    public void updateTick(World world, int x, int y, int z, Random random) {

        super.updateTick(world, x, y, z, random);
        
        if (random.nextInt(3) == 0) {
            if (freezeProgress < 400) {
                ++freezeProgress;
            } else {
                if (random.nextInt(2) == 0) {
                    world.setBlock(x, y, z, 0);
                    world.setBlock(x, y, z, BlocksHelper.concrete.blockID);
                }
            }
        }
    }

    public String getTextureName() {
        return this.textureName;
    }

    private String getSideTextureName() {
        return this.sideTextureName;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister reg) {
        this.blockIcon = reg.registerIcon("communityMod:" + this.getTextureName());
        this.side = reg.registerIcon("communityMod:" + this.getSideTextureName());
        this.bottom = reg.registerIcon("communityMod:liquidConcreteFlowing");
    }

    @Override
    public Icon getBlockTextureFromSideAndMetadata(int side, int metadata) {
        if (side == 1)
            return blockIcon;
        else if (side == 0)
            return bottom;

        return this.side;
    }

    @Override
    public int stillLiquidId() {
        return BlocksHelper.liquidConcreteStill.blockID;
    }

    @Override
    public boolean isMetaSensitive() {
        return false;
    }

    @Override
    public int stillLiquidMeta() {
        return 0;
    }

    @Override
    public boolean isBlockReplaceable(World world, int i, int j, int k) {
        return true;
    }
}