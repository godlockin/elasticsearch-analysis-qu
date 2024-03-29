/**
 * Stanford Core NLP 中文分词器 版本 1.0
 * Stanford Core NLP Analyzer Release 1.0
 *
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * 源代码由陈晨(stevenchenworking@gmail.com)提供
 * provided by Steven Chen
 * [Stanford CoreNLP home page](https://stanfordnlp.github.io/CoreNLP/index.html)
 * [Stanford CoreNLP GitHub page](https://github.com/stanfordnlp/CoreNLP)
 */
package org.elasticsearch.plugin.analysis;

import org.apache.lucene.analysis.Analyzer;
import org.elasticsearch.index.analysis.AnalyzerProvider;
import org.elasticsearch.index.analysis.QuAnalyzerProvider;
import org.elasticsearch.index.analysis.QuTokenizerFactory;
import org.elasticsearch.index.analysis.TokenizerFactory;
import org.elasticsearch.indices.analysis.AnalysisModule;
import org.elasticsearch.plugins.AnalysisPlugin;
import org.elasticsearch.plugins.Plugin;

import java.util.HashMap;
import java.util.Map;

public class AnalysisQuPlugin extends Plugin implements AnalysisPlugin {

	private static final String PLUGIN_NAME = "analysis-qu";

    @Override
    public Map<String, AnalysisModule.AnalysisProvider<TokenizerFactory>> getTokenizers() {
        Map<String, AnalysisModule.AnalysisProvider<TokenizerFactory>> extra = new HashMap<>();
        extra.put(PLUGIN_NAME, QuTokenizerFactory::new);
        return extra;
    }

    @Override
    public Map<String, AnalysisModule.AnalysisProvider<AnalyzerProvider<? extends Analyzer>>> getAnalyzers() {
        Map<String, AnalysisModule.AnalysisProvider<AnalyzerProvider<? extends Analyzer>>> extra = new HashMap<>();
        extra.put(PLUGIN_NAME, QuAnalyzerProvider::new);
        return extra;
    }

}
