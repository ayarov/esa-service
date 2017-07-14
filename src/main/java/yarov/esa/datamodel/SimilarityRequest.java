package yarov.esa.datamodel;

import java.io.Serializable;

public class SimilarityRequest implements Serializable {
    public String textA = "";
    public String textB = "";
    public boolean encoded = false;
}
