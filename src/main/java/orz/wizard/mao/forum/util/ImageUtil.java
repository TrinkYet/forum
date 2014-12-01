package orz.wizard.mao.forum.util;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class ImageUtil {
    public static BufferedImage selectImageArea(BufferedImage image, int x, int y, int w, int h) {
        if (image == null) {
            return null;
        } else {
            int srcW = image.getWidth();
            int srcH = image.getHeight();
            if (x < 0) {
                x = 0;
            }
            if (y < 0) {
                y = 0;
            }
            if (x >= srcW || y >= srcH || w < 1 || h < 1) {
                return null;
            }
            BufferedImage image2 = new BufferedImage(w, h, image.getType());
            Graphics g = image2.getGraphics();
            g.drawImage(image, 0, 0, w - 1, h - 1, x, y, Math.min(x + w - 1, srcW - 1), Math.min(y + h - 1, srcH - 1), null);
            return image2;
        }
    }
}
