package svg;

import lombok.experimental.FieldNameConstants;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@FieldNameConstants
public class Path {

    public String strokeWidth;
    public String strokeColor;
    public String fillColor;
    public String pathData;
    public static List<String> colors = Arrays.asList("#AB95DF", "#F4E878", "#D04C59", "#2F0D42", "#056C81", "#478D36", "#FA4C2C", "#B931E9", "#751B1B", "#898778", "#CF9E3E", "#6C66EB", "#52FF43", "#D60950", "#CCFB2B", "#F065A0", "#05C6A5");
    public static Random random = new Random();

    public Path(String strokeWidth, String strokeColor, String fillColor, String pathData) {
        this.strokeWidth = strokeWidth;
        this.strokeColor = strokeColor;
        this.fillColor = fillColor;
        this.pathData = pathData;
    }

    public Path(String pathData) {
        this.strokeWidth = "0.2";
        this.strokeColor = "@color/black";
        this.fillColor = colors.get(random.nextInt(colors.size()));
        this.pathData = pathData;
    }
}
