package com.mojang.rubydung.character;

import com.mojang.rubydung.Entity;
import com.mojang.rubydung.Textures;
import com.mojang.rubydung.level.Level;
import org.lwjgl.opengl.GL11;

public class Zombie extends Entity {
   public Cube head;
   public Cube body;
   public Cube arm0;
   public Cube arm1;
   public Cube leg0;
   public Cube leg1;
   public float rot;
   public float timeOffs;
   public float speed;
   public float rotA = (float)(Math.random() + (double)1.0F) * 0.01F;

   public Zombie(Level level, float x, float y, float z) {
      super(level);
      this.x = x;
      this.y = y;
      this.z = z;
      this.timeOffs = (float)Math.random() * 1239813.0F;
      this.rot = (float)(Math.random() * Math.PI * (double)2.0F);
      this.speed = 1.0F;
      this.head = new Cube(0, 0);
      this.head.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8);
      this.body = new Cube(16, 16);
      this.body.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4);
      this.arm0 = new Cube(40, 16);
      this.arm0.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4);
      this.arm0.setPos(-5.0F, 2.0F, 0.0F);
      this.arm1 = new Cube(40, 16);
      this.arm1.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4);
      this.arm1.setPos(5.0F, 2.0F, 0.0F);
      this.leg0 = new Cube(0, 16);
      this.leg0.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4);
      this.leg0.setPos(-2.0F, 12.0F, 0.0F);
      this.leg1 = new Cube(0, 16);
      this.leg1.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4);
      this.leg1.setPos(2.0F, 12.0F, 0.0F);
   }

   public void tick() {
      this.xo = this.x;
      this.yo = this.y;
      this.zo = this.z;
      float xa = 0.0F;
      float ya = 0.0F;
      this.rot += this.rotA;
      this.rotA = (float)((double)this.rotA * 0.99);
      this.rotA = (float)((double)this.rotA + (Math.random() - Math.random()) * Math.random() * Math.random() * (double)0.01F);
      xa = (float)Math.sin((double)this.rot);
      ya = (float)Math.cos((double)this.rot);
      if (this.onGround && Math.random() < 0.01) {
         this.yd = 0.12F;
      }

      this.moveRelative(xa, ya, this.onGround ? 0.02F : 0.005F);
      this.yd = (float)((double)this.yd - 0.005);
      this.move(this.xd, this.yd, this.zd);
      this.xd *= 0.91F;
      this.yd *= 0.98F;
      this.zd *= 0.91F;
      if (this.y > 100.0F) {
         this.resetPos();
      }

      if (this.onGround) {
         this.xd *= 0.8F;
         this.zd *= 0.8F;
      }

   }

   public void render(float a) {
      GL11.glEnable(3553);
      GL11.glBindTexture(3553, Textures.loadTexture("/char.png", 9728));
      GL11.glPushMatrix();
      double time = (double)System.nanoTime() / (double)1.0E9F * (double)10.0F * (double)this.speed + (double)this.timeOffs;
      float size = 0.058333334F;
      float yy = (float)(-Math.abs(Math.sin(time * 0.6662)) * (double)5.0F - (double)23.0F);
      GL11.glTranslatef(this.xo + (this.x - this.xo) * a, this.yo + (this.y - this.yo) * a, this.zo + (this.z - this.zo) * a);
      GL11.glScalef(1.0F, -1.0F, 1.0F);
      GL11.glScalef(size, size, size);
      GL11.glTranslatef(0.0F, yy, 0.0F);
      float c = 57.29578F;
      GL11.glRotatef(this.rot * c + 180.0F, 0.0F, 1.0F, 0.0F);
      this.head.yRot = (float)Math.sin(time * 0.83) * 1.0F;
      this.head.xRot = (float)Math.sin(time) * 0.8F;
      this.arm0.xRot = (float)Math.sin(time * 0.6662 + Math.PI) * 2.0F;
      this.arm0.zRot = (float)(Math.sin(time * 0.2312) + (double)1.0F) * 1.0F;
      this.arm1.xRot = (float)Math.sin(time * 0.6662) * 2.0F;
      this.arm1.zRot = (float)(Math.sin(time * 0.2812) - (double)1.0F) * 1.0F;
      this.leg0.xRot = (float)Math.sin(time * 0.6662) * 1.4F;
      this.leg1.xRot = (float)Math.sin(time * 0.6662 + Math.PI) * 1.4F;
      this.head.render();
      this.body.render();
      this.arm0.render();
      this.arm1.render();
      this.leg0.render();
      this.leg1.render();
      GL11.glPopMatrix();
      GL11.glDisable(3553);
   }
}
