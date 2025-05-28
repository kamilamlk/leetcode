package com.leetcode.ocp.api.drawable;

public class LocalClassClient {
    public static void main(String[] args) {
        IDrawable[] drawables = {
            new Painter().createACircle(5),
            Painter.createGraph(),
        };
        for (IDrawable drawable : drawables) {
            drawable.draw();
        }
        System.out.println("-------------------");
        System.out.println("Local class Names: ");
        System.out.println(drawables[0].getClass().getName());
        System.out.println(drawables[1].getClass().getName());

        System.out.println("-------------------");
        LocalTypes localTypes = new LocalTypes();
        localTypes.nonStaticMethod(10);
    }
}
