/**
 * 
 */
package analyzer.solr5.jieba;

import com.huaban.analysis.jieba.JiebaSegmenter.SegMode;
import org.apache.lucene.analysis.Analyzer;

/**
 * @author zhangcheng
 *
 */
public class JiebaAnalyzer extends Analyzer {
	
	private String segMode;

	/**
	 * 
	 */
	public JiebaAnalyzer() {
		this(SegMode.SEARCH.name());
	}
	
	public JiebaAnalyzer(String segMode) {
		this.segMode = segMode;
	}

	/**
	 * @param reuseStrategy
	 */
	public JiebaAnalyzer(ReuseStrategy reuseStrategy) {
		super(reuseStrategy);
	}


	@Override
	protected TokenStreamComponents createComponents(String field) {
		return new TokenStreamComponents(new JiebaTokenizer(this.segMode, null));
	}
}
