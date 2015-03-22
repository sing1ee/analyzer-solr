package analyzer.solr5.stanford;

import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.util.TokenizerFactory;
import org.apache.lucene.util.AttributeFactory;

import java.io.Reader;
import java.util.Map;

public class StanfordTokenizerFactory extends TokenizerFactory {

	private String modelDir;

	public StanfordTokenizerFactory(Map<String, String> args) {
		super(args);
		if (args.containsKey("modelDir")) {
			modelDir = args.get("modelDir");
		} else {
			System.err.println("we need model dir for fnlp");
			System.exit(1);
		}
	}

	@Override
	public Tokenizer create(AttributeFactory arg0) {

		return new StanfordTokenizer(modelDir);
	}

	public String getModelDir() {

		return modelDir;
	}

	public void setModelDir(String modelDir) {

		this.modelDir = modelDir;
	}
}
