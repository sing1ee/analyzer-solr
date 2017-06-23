/**
 * 
 */
package analyzer.solr5.stanford;

import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.util.AttributeFactory;

import java.io.IOException;

/**
 * @author zhangcheng
 *
 */
public class StanfordTokenizer extends Tokenizer {

	private StanfordAdapter adapter;
	
	private CharTermAttribute termAtt;

	public StanfordTokenizer(String modelDir) {
		 this.termAtt = addAttribute(CharTermAttribute.class);
		adapter = StanfordAdapter.getInstance(input, modelDir);
	}

	/**
	 * @param factory
	 */
	public StanfordTokenizer(AttributeFactory factory) {

		super(factory);
	}

	@Override
	public boolean incrementToken() throws IOException {
		clearAttributes();
		if(adapter.hasNext()){
			String token = adapter.next();
			termAtt.append(token);
			termAtt.setLength(token.length());
			return true;
		}
		return false;
	}

	@Override
	public void reset() throws IOException {
		super.reset();
		adapter.reset(this.input);
	}
}