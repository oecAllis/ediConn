
package com.oecgroup.parser.edi.loops;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "qualifier")
public class Qualifier {

    @XmlAttribute(name = "position", required = true)
    private int position;

    @XmlElement(name = "value", required = true)
    private List<String> values;

    public int getPosition() {
        return position;
    }

    public List<String> getValues() {
        return values;
    }
}
