package lighting;

import org.w3c.dom.Element;

import parser.Parser;
import primitives.*;

public class AmbientLight extends Light{
    /**
     * Constructor
     */
    public AmbientLight() {
        super(Color.BLACK);
    }

    /**
     * Constructor
     * 
     * @param intensity
     */
    public AmbientLight(Color iA, Double3 kA) {
        super(iA.scale(kA));
    }

    public AmbientLight(Color iA, double kA){
        super(iA.scale(new Double3(kA)));
    }

    public AmbientLight(Element element){
        super(new Color(Parser.parseDouble3(element.getAttribute("color"))));
    }
}
