package yarov.esa;

import be.vanoosten.esa.EnwikiFactory;
import be.vanoosten.esa.tools.SemanticSimilarityTool;

public class EsaProvider {

    private static EsaProvider instance = null;
    private SemanticSimilarityTool esa;

    private EsaProvider() {
        esa = new SemanticSimilarityTool(new EnwikiFactory().getOrCreateVectorizer());
    }

    public static EsaProvider getInstance() {
        if(instance == null) {
            instance = new EsaProvider();
        }
        return instance;
    }

    public float findSemanticSimilarity(String textA, String textB) {
        return esa.findSemanticSimilarity(textA, textB);
    }

    public float findSemanticSimilarity(byte[] bytesA, byte[] bytesB) {
        return esa.findSemanticSimilarity(bytesA, bytesB);
    }
}
