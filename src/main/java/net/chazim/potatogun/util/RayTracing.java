package net.chazim.potatogun.util;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;

public class RayTracing
{

    private static RayTracing _instance;
    private MovingObjectPosition target = null;
    private Minecraft mc = Minecraft.getMinecraft();

    private RayTracing()
    {
    }

    public static RayTracing instance()
    {
        if (_instance == null)
            _instance = new RayTracing();
        return _instance;
    }

    public void fire(double dist)
    {
        if (mc.objectMouseOver != null && mc.objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.ENTITY)
        {
            this.target = mc.objectMouseOver;
            return;
        }

        EntityLivingBase viewpoint = mc.renderViewEntity;
        if (viewpoint == null) return;

        this.target = this.rayTrace(viewpoint, dist, 0);

        if (this.target == null) return;
    }

    public MovingObjectPosition getTarget()
    {
        return this.target;
    }

    public MovingObjectPosition rayTrace(EntityLivingBase entity, double par1, float par3)
    {
        Vec3 vec3 = entity.getPosition(par3);
        Vec3 vec31 = entity.getLook(par3);
        Vec3 vec32 = vec3.addVector(vec31.xCoord * par1, vec31.yCoord * par1, vec31.zCoord * par1);

        return entity.worldObj.rayTraceBlocks(vec3, vec32, false);
    }
}
