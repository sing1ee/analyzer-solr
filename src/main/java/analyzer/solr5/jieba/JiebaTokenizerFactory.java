package analyzer.solr5.jieba;

import com.huaban.analysis.jieba.JiebaSegmenter.SegMode;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.util.TokenizerFactory;
import org.apache.lucene.util.AttributeFactory;

import java.io.Reader;
import java.util.Map;

public class JiebaTokenizerFactory extends TokenizerFactory {
	
	private String segMode;
	
	public JiebaTokenizerFactory(Map<String, String> args) {
		super(args);
        if (null == args.get("segMode")) {
        	segMode = SegMode.SEARCH.name();
        } else {
        	segMode = args.get("segMode");
        }
	}

	@Override
	public Tokenizer create(AttributeFactory arg0) {
		return new JiebaTokenizer(segMode);
	}

	public String getSegMode() {
		return segMode;
	}

	public void setSegMode(String segMode) {
		this.segMode = segMode;
	}

}
