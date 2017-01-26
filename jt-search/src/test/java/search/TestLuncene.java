package search;

import java.io.File;
import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.LongField;
import org.apache.lucene.document.StringField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

public class TestLuncene {
    
    public static void main(String[] args) throws IOException {
        /*
         * 业务：商品信息
         * 创建文档 Document
         * 进行索引
         */
        Document doc = new Document();
        doc.add(new StringField("title", "鬼斧神工", Store.YES));
        doc.add(new LongField("price", 11100, Store.YES));
        
        // 找一个目录，将创建是索引文件放入到这个目录下
        Directory dir = FSDirectory.open(new File("index"));
        
        // 标准分词，基于英文分词
        Analyzer analyzer = new StandardAnalyzer();
        
        // 版本必须对应 jar 版本 
        IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_4_10_2, analyzer);
        
        // 定义索引对象
        IndexWriter indexWriter = new IndexWriter(dir, config);
        
        // 写入数据
        indexWriter.addDocument(doc);
        
        // 关闭
        indexWriter.close();
    }
}






