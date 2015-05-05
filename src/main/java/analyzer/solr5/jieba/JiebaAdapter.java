package analyzer.solr5.jieba;

import com.huaban.analysis.jieba.JiebaSegmenter;
import com.huaban.analysis.jieba.JiebaSegmenter.SegMode;
import com.huaban.analysis.jieba.SegToken;
import com.huaban.analysis.jieba.WordDictionary;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;
import java.util.List;

public class JiebaAdapter implements Iterator<SegToken> {

	private final JiebaSegmenter jiebaTagger;

	private final SegMode segMode;

	private Iterator<SegToken> tokens;

	private String raw = null;

	public JiebaAdapter(Reader input, String segModeName, String userDictPath) {

		this.jiebaTagger = new JiebaSegmenter();

		if (null == segModeName) {
			segMode = SegMode.SEARCH;
		} else {
			segMode = SegMode.valueOf(segModeName);
		}

		if (null != userDictPath) {
			try{
				WordDictionary dictAdd = WordDictionary.getInstance();
				File file = new File(userDictPath);
				dictAdd.loadUserDict(file);
				System.out.println("load userDict: " + userDictPath + " succ!");
			} catch (Exception e) {
				System.err.println("load userDict: " + userDictPath + " fail!");
			}
		}
	}

	public synchronized void reset(Reader input) {
		try {
			StringBuilder bdr = new StringBuilder();
			char[] buf = new char[1024];
			int size = 0;
			while ((size = input.read(buf, 0, buf.length)) != -1) {
				String tempstr = new String(buf, 0, size);
				bdr.append(tempstr);
			}
			raw = bdr.toString().trim();
		} catch (IOException e) {
			e.printStackTrace();
		}

		List<SegToken> list = jiebaTagger.process(raw, segMode);
		tokens = list.iterator();
	}

	@Override
	public boolean hasNext() {
		return tokens.hasNext();
	}

	@Override
	public SegToken next() {
		return tokens.next();
	}

	@Override
	public void remove() {
		tokens.remove();
	}
}
