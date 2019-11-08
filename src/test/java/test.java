import edu.stanford.nlp.coref.CorefCoreAnnotations;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.neural.rnn.RNNCoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.CoreSentence;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.util.CoreMap;
import edu.stanford.nlp.util.concurrent.AtomicDouble;
import org.ejml.simple.SimpleMatrix;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.stream.Collectors;

public class test {
    public static void main(String[] args) {

        Random random = new Random();
        for (int i = 0; i < 100; i++)
//            System.out.println(System.currentTimeMillis());
            System.out.println(random.nextInt(2));
//        test1();
//        test2();
//        test3();
//        test4();
    }

    public static void test4() {

        Properties props1 = new Properties();
        props1.setProperty("annotators", "tokenize,ssplit,pos,lemma,ner,parse,coref,sentiment,kbp");
        props1.setProperty("tokenize.language", "en");
        props1.setProperty("threads", "8");
        props1.setProperty("entitylink.wikidict", "edu/stanford/nlp/models/kbp/english/wikidict.tab.gz");
        StanfordCoreNLP se = new StanfordCoreNLP(props1);

        Annotation ae = se.process("Machine learning is the foundation of countless important applications.");
        System.out.println(ae.toString());

        Properties props = new Properties();
        props.setProperty("annotators", "tokenize, ssplit, pos, lemma, ner, parse, coref, sentiment");
        props.setProperty("tokenize.language", "zh");

        props.setProperty("ssplit.boundaryTokenRegex", "[.。]|[!?！？]+");

        props.setProperty("segment.model", "edu/stanford/nlp/models/segmenter/chinese/ctb.gz");
        props.setProperty("segment.sighanCorporaDict", "edu/stanford/nlp/models/segmenter/chinese");
        props.setProperty("segment.serDictionary", "edu/stanford/nlp/models/segmenter/chinese/dict-chris6.ser.gz");
        props.setProperty("segment.sighanPostProcessing", "true");
        props.setProperty("pos.model", "edu/stanford/nlp/models/pos-tagger/chinese-distsim/chinese-distsim.tagger");
        props.setProperty("parse.model", "edu/stanford/nlp/models/srparser/chineseSR.ser.gz");
        props.setProperty("depparse.model   ", "edu/stanford/nlp/models/parser/nndep/UD_Chinese.gz");
        props.setProperty("depparse.language", "chinese");

        props.setProperty("ner.language", "chinese");
        props.setProperty("ner.model", "edu/stanford/nlp/models/ner/chinese.misc.distsim.crf.ser.gz");
        props.setProperty("ner.applyNumericClassifiers", "true");
        props.setProperty("ner.useSUTime", "false");

        props.setProperty("regexner.mapping", "edu/stanford/nlp/models/kbp/cn_regexner_mapping.tab");
        props.setProperty("regexner.validpospattern", "^(NR|NN|JJ).*");
        props.setProperty("regexner.ignorecase", "true");
        props.setProperty("regexner.noDefaultOverwriteLabels", "CITY");

        props.setProperty("coref.sieves", "ChineseHeadMatch, ExactStringMatch, PreciseConstructs, StrictHeadMatch1, StrictHeadMatch2, StrictHeadMatch3, StrictHeadMatch4, PronounMatch");
        props.setProperty("coref.input.type", "raw");
        props.setProperty("coref.postprocessing", "true");
        props.setProperty("coref.calculateFeatureImportance", "false");
        props.setProperty("coref.useConstituencyTree", "true");
        props.setProperty("coref.useSemantics", "false");
        props.setProperty("coref.algorithm", "hybrid");
        props.setProperty("coref.language", "zh");
        props.setProperty("coref.defaultPronounAgreement", "true");
        props.setProperty("coref.zh.dict", "edu/stanford/nlp/models/dcoref/zh-attributes.txt.gz");
        props.setProperty("coref.print.md.log", "false");
        props.setProperty("coref.md.type", "RULE");
        props.setProperty("coref.md.liberalChineseMD", "false");
        props.setProperty("kbp.semgrex", "edu/stanford/nlp/models/kbp/chinese/semgrex");
        props.setProperty("kbp.tokensregex", "edu/stanford/nlp/models/kbp/chinese/tokensregex");
        props.setProperty("kbp.model", "none");
        props.setProperty("entitylink.wikidict", "edu/stanford/nlp/models/kbp/wikidict_chinese.tsv.gz");

        StanfordCoreNLP sc = new StanfordCoreNLP(props);
        Annotation ac = sc.process("标签扩展的本质是对每个标签找到和它相似的标签，也就是计算标签之间的相似度。");
        System.out.println(ac.toString());
    }

    public static void test3() {
        String text = "    aabb\n\nccdd    \r\n\r\neeff\ngghh\r\niijj     kkll     \n\nz";
        System.out.println("===========================");
        System.out.println(text);
        System.out.println("===========================");
        String base = text.replaceAll("[ ]+", " ").replaceAll("(?m)^\\s*$(\\n|\\r\\n)", "");
        System.out.println("===========================");
        System.out.println(base);
        System.out.println("===========================");
    }

    public static void test1() {
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize, ssplit, pos, lemma, ner, parse, coref, sentiment");
        props.setProperty("tokenize.language", "zh");

        props.setProperty("ssplit.boundaryTokenRegex", "[.。]|[!?！？]+");

        props.setProperty("segment.model", "edu/stanford/nlp/models/segmenter/chinese/ctb.gz");
        props.setProperty("segment.sighanCorporaDict", "edu/stanford/nlp/models/segmenter/chinese");
        props.setProperty("segment.serDictionary", "edu/stanford/nlp/models/segmenter/chinese/dict-chris6.ser.gz");
        props.setProperty("segment.sighanPostProcessing", "true");
        props.setProperty("pos.model", "edu/stanford/nlp/models/pos-tagger/chinese-distsim/chinese-distsim.tagger");
        props.setProperty("parse.model", "edu/stanford/nlp/models/srparser/chineseSR.ser.gz");
        props.setProperty("depparse.model   ", "edu/stanford/nlp/models/parser/nndep/UD_Chinese.gz");
        props.setProperty("depparse.language", "chinese");

        props.setProperty("ner.language", "chinese");
        props.setProperty("ner.model", "edu/stanford/nlp/models/ner/chinese.misc.distsim.crf.ser.gz");
        props.setProperty("ner.applyNumericClassifiers", "true");
        props.setProperty("ner.useSUTime", "false");

        props.setProperty("regexner.mapping", "edu/stanford/nlp/models/kbp/cn_regexner_mapping.tab");
        props.setProperty("regexner.validpospattern", "^(NR|NN|JJ).*");
        props.setProperty("regexner.ignorecase", "true");
        props.setProperty("regexner.noDefaultOverwriteLabels", "CITY");

        props.setProperty("coref.sieves", "ChineseHeadMatch, ExactStringMatch, PreciseConstructs, StrictHeadMatch1, StrictHeadMatch2, StrictHeadMatch3, StrictHeadMatch4, PronounMatch");
        props.setProperty("coref.input.type", "raw");
        props.setProperty("coref.postprocessing", "true");
        props.setProperty("coref.calculateFeatureImportance", "false");
        props.setProperty("coref.useConstituencyTree", "true");
        props.setProperty("coref.useSemantics", "false");
        props.setProperty("coref.algorithm", "hybrid");
        props.setProperty("coref.language", "zh");
        props.setProperty("coref.defaultPronounAgreement", "true");
        props.setProperty("coref.zh.dict", "edu/stanford/nlp/models/dcoref/zh-attributes.txt.gz");
        props.setProperty("coref.print.md.log", "false");
        props.setProperty("coref.md.type", "RULE");
        props.setProperty("coref.md.liberalChineseMD", "false");
        props.setProperty("kbp.semgrex", "edu/stanford/nlp/models/kbp/chinese/semgrex");
        props.setProperty("kbp.tokensregex", "edu/stanford/nlp/models/kbp/chinese/tokensregex");
        props.setProperty("kbp.model", "none");
        props.setProperty("entitylink.wikidict", "edu/stanford/nlp/models/kbp/wikidict_chinese.tsv.gz");

        StanfordCoreNLP s = new StanfordCoreNLP(props);
//        System.out.println(s.getEncoding());
        List words = new ArrayList<>();
        String base = "如果节点id中包含\":\"，如果为\"data:true\"为添加集群中的数据节点，\"data:false\"为删除集群中的数据节点；如果为\"master:true\"为添加集群中的master节点，\"master:false\"为删除集群中的master节点；否则用key和value匹配集群中节点的属性信息，添加匹配到的nodeId。";

//        String prefix = "港股中小指版";
//        Stream.of("暴跌", "跌", "小跌", "小涨", "涨", "暴涨").forEach(x -> build(s, prefix + x));
//
//        System.out.println("-------------");
//        Stream.of("暴跌", "跌", "小跌", "小涨", "涨", "暴涨").forEach(x -> build(s, x + prefix));

        System.out.println("-------------");
//        Stream.of("暴跌", "跌", "跌了", "小跌", "小涨", "涨", "涨了", "暴涨", "了").forEach(x -> build(s, x));
//        Annotation a = s.process(base);
//        for (CoreMap t : a.get(CoreAnnotations.TokensAnnotation.class)) {
//            System.out.println(t);
//            String word = t.get(CoreAnnotations.TextAnnotation.class);
//            System.out.println(word);
//            words.add(word);
//            String pos = t.get(CoreAnnotations.PartOfSpeechAnnotation.class);
//            System.out.println(pos);
//
//        }


//


//        base += "General Mills announced a voluntary recall on Jan. 23. of their Gold Medal Unbleached Flour, due to the detection of Salmonella during routine sampling. The company is urging consumers to check their pantries for the presence of 5-pound bags of Gold Medal Unbleached Flour with a “better if used by date of April 20, 2020.” Hello World. I have $1.6 billion. Do you know the result of 2.3 * 1.5 .Sen.Lindsey O.Graham (R-S.C.) said that the \"U.S. does not recognize the Maduro regime as the government of Venezuela\"";
//        base += "The CDC estimates there are 1.2 million cases annually in the U.S., with about 450 ending in death. People can become infected by consuming food and water contaminated with Salmonella bacteria, which typically happens after exposure to small amounts of animal feces.";
//        base += "President Xi Jinping of China, on his first state visit to the United States, showed off his familiarity with American history and pop culture on Tuesday night. With the great partner U.S.A., China will be better and better.";
        base += "下面来看第二个变形，与第一种变形相同的是都会接受一个BinaryOperator函数接口，不同的是其会接受一个identity参数，用来指定Stream循环的初始值。如果Stream为空，就直接返回该值。另一方面，该方法不会返回Optional，因为该方法不会出现null。";




//        Annotation a = s.process(base);
//        for (CoreMap sentence : a.get(CoreAnnotations.SentencesAnnotation.class)) {
//            sentence.get(CoreAnnotations.SentencesAnnotation.class).forEach(x -> System.out.println(x.get(CoreAnnotations.TextAnnotation.class)));
//        }

//        CoreDocument document = new CoreDocument(base);
        // annnotate the document
//        s.annotate(document);

//        List<CoreSentence> sentences = document.sentences();
//
//        for(CoreSentence sentence: sentences) {
//
//            // traversing the words in the current sentence
//            // a CoreLabel is a CoreMap with additional token-specific methods
//            for (CoreLabel token : sentence.tokens()) {
//                // this is the text of the token
//                String word = token.get(CoreAnnotations.TextAnnotation.class);
//                System.out.println("word:" + word);
//                // this is the POS tag of the token
//                String pos = token.get(CoreAnnotations.PartOfSpeechAnnotation.class);
//                System.out.println("pos:" + pos);
//                // this is the NER label of the token
//                String ne = token.get(CoreAnnotations.NamedEntityTagAnnotation.class);
//                System.out.println("ne:" + ne);
//            }
//            System.out.println("-------------");
//        }
        base += "《汇率的博弈》（中信出版社）一书的作者管涛，是我多年的同事和朋友，他长期从事外汇管理政策的研究和制定，在人民币汇率和国际收支领域有着很深的造诣。他在2015年离开政府部门加入中国金融四十人论坛（CF40），成为CF40智库的专职高级研究员。虽然工作岗位变了，但我知道他醉心于政策研究、报效国家的情怀并没有变，中国的宏观经济、货币政策尤其是汇率政策仍然是他的主要研究方向。这次出版的新作《汇率的博弈》，就是他最新思考的结晶。\n书中对我国经济体量和国际影响进行了新的评估定位，明确提出了大型开放经济体在汇率政策选择上必须充分考虑国情，只有优先考虑货币政策的独立性，方可牢牢把握汇率选择的主动权；全面梳理阐述了我国汇率制度的历史演进和当下的资本流动形势，指出现在重新思考人民币汇率政策正当其时，不要浪费了汇率稳定为改革争取和创造的调整时间；客观分析了汇率制度选择“角点解”和“中间解”的利弊，强调汇率选择并无一劳永逸的最优解，因为没有一种汇率制度适合所有国家以及一个国家的所有时期，成功的汇率转型不仅需要谋定而后动，更需要“功夫在诗外”的全方位努力，并由此对汇率改革需要配套的相关措施提出了中肯建议。毋庸讳言，这是一部颇见学术功力又极具现实意义的著作。作者在书中讨论了很多发人深省的问题，虽不能一一跟进，但我也想借此机会谈点个人看法。\n的确，作为正在崛起的发展中大国，一个更加灵活和更富弹性的汇率更有利于保持货币政策的独立性，也更有利于在资本项目可兑换和人民币国际化方面采取更为进取的策略。因此，从有管理浮动逐步转向清洁浮动应是我国较为现实可行、也是大势所趋的汇率选择。从国际上看，目前仍然将法定货币盯住其他单一货币或一篮子货币的，主要为小型开放经济体（如中国香港和新加坡），它们将稳定汇率作为货币政策重要目标，主动放弃政策独立性。但主要发达经济体绝大多数采用自由浮动汇率制度，汇率水平由外汇市场供求决定；在金砖国家中，俄罗斯、巴西、印度和南非也都实行“浮动类”汇率制度。而我国现阶段因受多种因素影响，一方面金融市场深化程度不足、国际金融一体化程度不足，另一方面金融市场化改革尚未到位，经济体制不完善，对外贸易投资发展仍需要提供稳定的汇率锚作为支撑，故人民币汇率仍参考一篮子货币，汇率浮动也仍进行一定管理。这固然有助于减轻汇率超调对实体经济的冲击，但汇率双向浮动作为宏观经济“自动稳定器”的作用显然发挥不畅，一定程度上制约了货币政策自主性。\n梳理2014年以来我国资本跨境流动的变化，不难发现：资本流出压力在近三年之所以有所增加，一定程度上也与汇率灵活性不够、市场主体对经济和资产保值的信心不足有关。从长期来看，人民币汇率主要取决于经济基本面，我国经济是健康的，人民币汇率并不存在持续贬值的基础。但由于当前人民币汇率并未趋向均衡，因而不可避免地面临资本管制和维护市场信心的双重挑战。从影响市场预期的其他几大因素来看，投机力量显然更关注中国经济的阶段性下行压力，从而借机炒作人民币汇率贬值预期；涉及实业投资的企业海外投资也在一定程度上加大了资本流出的压力；主要经济体货币政策的大放大收更是导致国际资本跨境大幅流动。\n不一致或混乱的市场预期加剧了人民币汇率的波动，也使我们不得不面临两种选择：要么继续通过干预和管制来稳定汇率，要么允许市场反映供求的变化。从人民币汇率改革的方向看，无疑应当是逐步退出常态式干预，走出浮动难的困境，形成以市场供求为基础、双向波动、有弹性的汇率运行机制，逐步过渡到清洁浮动，使人民币汇率政策主要承担起提高货币政策自主性、发挥国际收支自动调节机制的作用。何况随着我国进出口规模不断增加，以及海内外资金往来越来越频繁，经常项目和资本项目之间很难严格区分，资本项目管制的效果不断递减，难度有所上升。资本管制不仅无法有效解决汇率失衡的问题，还容易授人以柄——如美国就不时指责我国操纵人民币汇率，威胁对我国征收高额关税等惩罚性措施，并将之与贸易、国企改革、产能过剩等问题挂钩。此外，汇率市场化程度不高，管制减少的程度不够，金融市场开放程度自然也难以提高，导致我国目前仍处在市场开放和发展的起步阶段，无法更好地发挥汇率自动平衡国际收支的稳定器作用，来缓解资本流动压力，平衡跨境资本流动。\n当前，我国经济发展进入新常态，比较优势正在发生动态转移，企业“走出去”步伐加快，对金融支持体系的深度和广度提出了更高要求，继续坚定不移地推进人民币汇率形成机制市场化改革已是大势所趋。一方面要逐步减少外汇市场干预，增强人民币汇率弹性，提高对人民币汇率波动的容忍度，允许市场汇率在浮动区间内双向波动，以更加充分地反映市场供求和国际汇市变化，让市场在汇率形成机制中发挥更大作用；另一方面，要充分利用人民币加入特别提款权货币篮子的有利时机，夯实人民币储备货币地位，提高人民币“可自由使用”程度。不断完善与储备货币发行国地位相适应的宏观政策框架，提高宏观调控能力，使之更加市场化、更加灵活、政策透明度更高、政策沟通更有效，巩固国际社会对人民币的信心，降低由信心引发资本外流的概率。\n最后，我想说的是，如作者所言，实现人民币汇率清洁浮动是我国汇率形成机制改革的既定方向，但究竟如何平稳有序地从有管理浮动的此岸走向自由浮动的彼岸，是一篇亟待破题的大文章。从这个意义上说，本书是一次极好的尝试。衷心希望看到更多的后来者。\n（作者为中国人民银行行长助理）\n";


        System.out.println(base);
        System.out.println("=============================================================");


        base += "被告人:对? 关于王立军,有几个基本事实.首先,1月28日我是初次听到此事.并不相信谷开来会杀人.我跟11·15杀人案无关.我不是谷开来11·15杀人罪的共犯.这个大家都认可.实际上谷开来3月14日她在北京被抓走!" +
                "在这之前她一直非常确切地跟我说她没杀人,是王立军诬陷她.我在1月28日和次听到这个事时我不相信她会杀人." +
                "第二个事实,免王立军的局长.是多个因素.一个,我确实认为他诬陷谷开来.但我并不是想掩盖11·15,我是觉得他人品不好." +
                "因为谷开来和他是如胶似漆,谷开来对他是言听计从,那王立军也通过与谷开来的交往中打入了我的家庭." +
                "那现在发生这么严重的事.作为一个起码的人,要讲人格的话,你干吗不找谷开来商量,而跑我这里来说这些话?" +
                "第二个免他的原因,是他想要挟我.他多次谈他身体不好,打黑压力大,得罪了人." +
                "其实这是在表功.第三,徐某某给我反映了他有五六条问题.有记录.实际上免他是有这些原因的,绝不只是一个谷开来的原因.这是多因一果.";

//        SimpleSummariser sim = new SimpleSummariser();
//        String ans = sim.summarise(base, sentences.size());
//        System.out.println(ans);

        base += "程序员(英文Programmer)是从事程序开发、维护的专业人员。一般将程序员分为程序设计人员和程序编码人员，但两者的界限并不非常清楚，特别是在中国。软件从业人员分为初级程序员、高级程序员、系统分析员和项目经理四大类。";



        base += "算法可大致分为基本算法、数据结构的算法、数论算法、计算几何的算法、图的算法、动态规划以及数值分析、加密算法、排序算法、检索算法、随机化算法、并行算法、厄米变形模型、随机森林算法。\n" +
                "算法可以宽泛的分为三类，\n" +
                "一，有限的确定性算法，这类算法在有限的一段时间内终止。他们可能要花很长时间来执行指定的任务，但仍将在一定的时间内终止。这类算法得出的结果常取决于输入值。\n" +
                "二，有限的非确定算法，这类算法在有限的时间内终止。然而，对于一个（或一些）给定的数值，算法的结果并不是唯一的或确定的。\n" +
                "三，无限的算法，是那些由于没有定义终止定义条件，或定义的条件无法由输入的数据满足而不终止运行的算法。通常，无限算法的产生是由于未能确定的定义终止条件。";

        base = "今天，是布比四周岁的生日，感谢一路走来所有关心和支持布比的人们！  2015年3月6日，布比正式注册成立，这应该是国内第一家专注区块链技术和产品的创业公司。当时，“区块链”三个字还鲜有人知，布比创始团队当初选择这个领域，主要是坚信区块链这一新兴技术将有潜力改变世界，因为它是第一次在技术层面建立多方信任的分布式新技术。布比的初心，就是以分布式信任为核心，致力于打造新一代价值流通网络，让数字资产都自由流动起来。 自成立伊始，布比一直非常重视区块链技术创新，攻克了一系列技术难关，也完成了50多项发明专利申请，逐步形成大规模商用级的区块链技术和产品。自2015年底开始，布比团队积极投入到区块链产业应用探索，粗略统计来看，布比与20多个行业的1500多个合作伙伴做过场景方面的应用探索，其中，正式开展合作的企业客户超过120家，涉及保险、证券、银行、供应链金融、数字资产、供应链溯源、联合征信、数据可信共享等等领域，这些经历使得布比团队在区块链商业应用方面具备了非常深厚的积累。 自2017年，布比开始聚焦于核心业务的运营，因为，在创办布比的第一天，我坚信区块链公司一定不能是传统软件型公司，而是要成为运营型业务。到今天，布比旗下壹诺区块链贸易融资网络，已经无故障运行2年以上，链上已有15家金融机构、80多家核心企业、1000多家供应商，发生的融资额超过100亿。布比支持的BUMO商用级基础公链，正在成为公链领域的一支新秀，已经有数十个应用在对接上链，尤其是在数字券的发行、分销、兑付、交易全流程体系上有了重大突破。 未来，布比将秉持“技术+商业+社群+治理”的模式，加速推动贸易融资网络、数字资产网络的发展壮大。 在过去四年成长过程，布比获得了总计近2亿的股权融资，布比的投资机构不仅在资金上提供支持，还帮助优化商业模式和对接产业资源，感谢投资人对布比团队的信任！ 最后，感谢每一位布比小伙伴，正是大家的团结奋斗，才使布比从四年前的婴儿成长为今天的青少年。 蒋海 布比创始人兼CEO";

//        Annotation a = s.process(base);

//        CoreDocument document = new CoreDocument(base);
//        s.annotate(document);
//        List<CoreSentence> sentences = document.sentences();
//        sentences.stream().map(sentence -> sentence.tokens().stream().filter(token -> {
//            String word = token.get(CoreAnnotations.TextAnnotation.class);
//            return !(isBlank(word));
//        }).collect(Collectors.toList())).reduce(words,
//                (list, item) -> {
//                    list.addAll(item);
//                    return list;
//                });
//
//        words.forEach(x -> {
//            CoreLabel token = (CoreLabel) x;
//            System.out.println("Begin:" + token.beginPosition());
//            System.out.println("End:" + token.endPosition());
//            System.out.println("lemma:" + token.lemma());
//            System.out.println("ner:" + token.ner());
//            System.out.println("originalText:" + token.originalText());
//            System.out.println("tag:" + token.tag());
//            System.out.println("value:" + token.value());
//            System.out.println("word:" + token.word());
//            System.out.println("----------------");
//        });
//        Annotation a = s.process(base);
//        document.sentences().forEach(x -> {
//            System.out.println(x.text());
//        });

        System.out.println("----------------");
        Annotation a = s.process(base);
        System.out.println("-------------" + base + "-------------");
        for (CoreMap sentence : a.get(CoreAnnotations.SentencesAnnotation.class)) {

            Tree tree = sentence.get(SentimentCoreAnnotations.SentimentAnnotatedTree.class);
            SimpleMatrix sm = RNNCoreAnnotations.getPredictions(tree);
            String sentimentType = sentence.get(SentimentCoreAnnotations.SentimentClass.class);
            System.out.println("SentimentType:" + sentimentType);
        }
    }

    public static void test2() {
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize,ssplit,pos,lemma,ner,parse,coref,sentiment,kbp");
        props.setProperty("tokenize.language", "en");
        props.setProperty("threads", "8");
        props.setProperty("entitylink.wikidict", "edu/stanford/nlp/models/kbp/english/wikidict.tab.gz");

//        props.setProperty("parse.model", "edu/stanford/nlp/models/srparser/englishSR.ser.gz");


        StanfordCoreNLP s = new StanfordCoreNLP(props);

        List words = new ArrayList<>();
        String base = "General Mills - announced a voluntary recall on Jan. 23. of their Gold Medal Unbleached Flour, due to the detection of Salmonella during routine sampling. The company is urging consumers to check their pantries for the presence of 5-pound bags of Gold Medal Unbleached Flour with a “better if used by date of April 20, 2020.” Hello World. I have $1.6 billion. Do you know the result of 2.3 * 1.5 .Sen.Lindsey O.Graham (R-S.C.) said that the \"U.S. does not recognize the Maduro regime as the government of Venezuela\"";
        base += "The CDC estimates there are 1.2 million cases annually in the U.S., with about 450 ending in death. People can become infected by consuming food and water contaminated with Salmonella bacteria, which typically happens after exposure to small amounts of animal feces.";
        base += "President Xi Jinping of China, on his first state visit to the United States, showed off his familiarity with American history and pop culture on Tuesday night. With the great partner U.S.A., China will be better and better.";

        base = "BHUBANESWAR: A petition in the High Court of Orissa requires the state government to explain why it " +
                "seeks to grant Tata Steel further iron ore mines when the steelmaker already has captive mines well " +
                "above the prescribed cap. The HC is hearing a petition challenging the Odisha government’s " +
                "recommendation to increase the prescribed area cap allowed to an individual player, from 10 sq km to 75" +
                " sq km. The matter will be heard again on 25 March 2019. The move will allow Tata Steel to participate " +
                "in auctions. The PIL argument is that this would grant Tata Steel, already the beneficiary of a " +
                "“disproportionate allocation of natural resources in the state”, an unfair advantage over other " +
                "steelmakers in the state. Tata Steel already hold rights to area nearing 50 sq km, “which is 89 per " +
                "cent of the total mining lease (area) ever allocated to steel companies,” claims the petition. The " +
                "Mines and Minerals (Development and Regulation) Act 1957, amended in 2015, sets 10 sq km as the limit " +
                "for mining rights granted to an individual in a state. This may be relaxed for an individual, as has " +
                "been done for Tata Steel and state-owned Odisha Mining Corporation and as long as the state justifies " +
                "the grant. This time however, Naveen Patnaik’s government chose to ask the Centre for a sevenfold " +
                "increase in the area limit itself. The current PIL in the High Court of Orissa has been filed by " +
                "journalist Bijaya Kumar Misra but this isn’t the first time that Tata Steel’s efforts to secure future " +
                "raw material in the state has been challenged. Rival steelmaker JSW had moved the High Court of Delhi " +
                "last year against Tata Steel’s participation in two tendered iron ore blocks that have since been " +
                "withdrawn. According to state officials, the Mineral Auction Rules 2015 is silent on the subject of " +
                "total area, covered under Section 6 of the MMDR Act. It has been almost a year since the Odisha " +
                "government wrote to the Centre to increase this limit. The Centre, which has expanded the cap for " +
                "bauxite allowing state-owned Nalco to operate a second mine and expanded similarly the cap for " +
                "limestone mines in Maharashtra, has been unmoved by Odisha’s request. A senior official at the state’s " +
                "directorate of mines, who asked not to be named, said the ball was still in the Centre’s court. Tata " +
                "Steel, which has captive rights to chrome, iron ore, and manganese in the state going back almost a " +
                "hundred years, only commissioned its first steel plant in the state in November of 2015. It is " +
                "currently ramping up the 3 mt plant at Kalinganagar to 8 mt per annum and has similar expansion plans " +
                "for Bhushan Steel’s 5 mt plant in Angul which it acquired under the Insolvency and Bankruptcy Code. It " +
                "currently about half a dozen iron ore mines in the state that also serve its Jamshedpur plant’s needs. " +
                "Some of these will lapse in 2030. While the HC of Delhi was still hearing the matter, the Odisha " +
                "government decided to withdraw it notice inviting tender for Chandiposhi (of 33.7 million tonnes " +
                "reserve) and Purheibahal (38.3 mt) iron ore deposits, rendering JSW’s petition infructuous. According " +
                "to officials at the state directorate, at least 17 deposits ready to be auctioned have been withheld " +
                "for now. But for a combined prospecting and mining lease, the state is yet to actually grant a lease " +
                "for any of the five deposits auctioned since 2015. Defending the state government's right to lobby for " +
                "the steelproducer, he said “Here’s a plant that has been put up, and is visible before our eyes. Is its" +
                " wrong for the state to encourage value addition within the state? Unfortunately the state government " +
                "could not grant Posco the Khandadhar deposit, before the act was amended.”. Odisha, the country’s " +
                "biggest producer, is also home to several sick steelmakers that it failed to supply captive raw " +
                "material to. In 2012 the Supreme Court had ruled that Odisha had been \"highly unreasonable and " +
                "arbitrary\" in denying Bhushan Power and Steel a promised iron ore deposit the huge sums the firm had " +
                "invested in a steel plant. BPSL,recently acquired by Sajjan Jindal’s JSW under insolvency proceedings, " +
                "and Bhushan Steel emerged successful bidders, for two iron ore mines auctioned in Odisha, only a month " +
                "before being declared insolvent. This has also been challenged in the court.";

        CoreDocument document = new CoreDocument(base);
        s.annotate(document);
        List<CoreSentence> sentences = document.sentences();
        sentences.stream().map(sentence -> sentence.tokens().stream().filter(token -> {
            String word = token.get(CoreAnnotations.TextAnnotation.class);
            return !(isBlank(word));
        }).collect(Collectors.toList())).reduce(words,
                (list, item) -> {
                    list.addAll(item);
                    return list;
                });
        System.out.println("----------------");

        document.annotation().get(CorefCoreAnnotations.CorefMentionsAnnotation.class).forEach(x -> {
            System.out.println(x);
            System.out.println(x.spanToString());
        });
        System.out.println("----------------");

        words.forEach(x -> {
            CoreLabel token = (CoreLabel) x;
            System.out.println("Begin:" + token.beginPosition());
            System.out.println("End:" + token.endPosition());
            System.out.println("lemma:" + token.lemma());
            System.out.println("ner:" + token.ner());
            System.out.println("originalText:" + token.originalText());
            System.out.println("tag:" + token.tag());
            System.out.println("value:" + token.value());
            System.out.println("word:" + token.word());
            System.out.println("----------------");
        });

        System.out.println("----------------");
        Annotation a = s.process(base);
        System.out.println("-------------" + base + "-------------");
        for (CoreMap sentence : a.get(CoreAnnotations.SentencesAnnotation.class)) {

            Tree tree = sentence.get(SentimentCoreAnnotations.SentimentAnnotatedTree.class);
            SimpleMatrix sm = RNNCoreAnnotations.getPredictions(tree);
            String sentimentType = sentence.get(SentimentCoreAnnotations.SentimentClass.class);
            System.out.println("SentimentType:" + sentimentType);
        }
    }


    public static boolean isBlank(CharSequence cs) {
        int strLen;
        if (cs != null && (strLen = cs.length()) != 0) {
            for(int i = 0; i < strLen; ++i) {
                if (!Character.isWhitespace(cs.charAt(i))) {
                    return false;
                }
            }

            return true;
        } else {
            return true;
        }
    }

    private static void build(StanfordCoreNLP s, String base) {
        Annotation a = s.process(base);
        System.out.println("-------------" + base + "-------------");
        for (CoreMap sentence : a.get(CoreAnnotations.SentencesAnnotation.class)) {
            // this is the parse tree of the current sentence
            Tree tree = sentence.get(SentimentCoreAnnotations.SentimentAnnotatedTree.class);
            SimpleMatrix sm = RNNCoreAnnotations.getPredictions(tree);
            String sentimentType = sentence.get(SentimentCoreAnnotations.SentimentClass.class);

            Double VeryPositiveCore = sm.get(4);
            Double VeryPositive = (double) Math.round(VeryPositiveCore * 100d);
            System.out.println("VeryPositive:\t" + VeryPositive + "\t:\t" + VeryPositiveCore);

            Double PositiveCore = sm.get(3);
            Double Positive = (double) Math.round(PositiveCore * 100d);
            System.out.println("Positive:\t" + Positive + "\t:\t" + PositiveCore);

            Double NeutralCore = sm.get(2);
            Double Neutral = (double) Math.round(NeutralCore * 100d);
            System.out.println("Neutral:\t" + Neutral + "\t:\t" + NeutralCore);

            Double NegativeCore = sm.get(1);
            Double Negative = (double) Math.round(NegativeCore * 100d);
            System.out.println("Negative:\t" + Negative + "\t:\t" + NegativeCore);

            Double VeryNegativeCore = sm.get(0);
            Double VeryNegative = (double) Math.round(VeryNegativeCore * 100d);
            System.out.println("VeryNegative:\t" + VeryNegative + "\t:\t" + VeryNegativeCore);

            System.out.println("SentimentScore:" + RNNCoreAnnotations.getPredictedClass(tree));
            System.out.println("SentimentType:" + sentimentType);

            Double sum = VeryPositiveCore * 10 + PositiveCore * 5 + NeutralCore * 1 + NegativeCore * (-5) + VeryNegativeCore * (-10);
            System.out.println("Sum:" + sum);
        }
        System.out.println("-------------" + base + "-------------");

        AtomicDouble VeryPositiveCoreSum = new AtomicDouble(0D);
        AtomicDouble PositiveCoreSum = new AtomicDouble(0D);
        AtomicDouble NeutralCoreSum = new AtomicDouble(0D);
        AtomicDouble NegativeCoreSum = new AtomicDouble(0D);
        AtomicDouble VeryNegativeCoreSum = new AtomicDouble(0D);

        AtomicDouble VeryPositiveSum = new AtomicDouble(0D);
        AtomicDouble PositiveSum = new AtomicDouble(0D);
        AtomicDouble NeutralSum = new AtomicDouble(0D);
        AtomicDouble NegativeSum = new AtomicDouble(0D);
        AtomicDouble VeryNegativeSum = new AtomicDouble(0D);

        List<String> words = new ArrayList<>();
        for (CoreMap t : a.get(CoreAnnotations.TokensAnnotation.class)) {
//            System.out.println(t);
            String word = t.get(CoreAnnotations.TextAnnotation.class);
//            System.out.println(word);
            words.add(word);
//            String pos = t.get(CoreAnnotations.PartOfSpeechAnnotation.class);
//            System.out.println(pos);

        }
        words.forEach(word -> {
            System.out.println(word);
            Annotation a1 = s.process(word);
            for (CoreMap sentence : a1.get(CoreAnnotations.SentencesAnnotation.class)) {
                // this is the parse tree of the current sentence
                Tree tree = sentence.get(SentimentCoreAnnotations.SentimentAnnotatedTree.class);
                SimpleMatrix sm = RNNCoreAnnotations.getPredictions(tree);
                String sentimentType = sentence.get(SentimentCoreAnnotations.SentimentClass.class);

                Double VeryPositiveCore = sm.get(4);
                Double VeryPositive = (double) Math.round(VeryPositiveCore * 100d);
//                System.out.println("VeryPositive:\t" + VeryPositive + "\t:\t" + VeryPositiveCore);
                VeryPositiveSum.addAndGet(VeryPositive);
                VeryPositiveCoreSum.addAndGet(VeryPositiveCore);

                Double PositiveCore = sm.get(3);
                Double Positive = (double) Math.round(PositiveCore * 100d);
//                System.out.println("Positive:\t" + Positive + "\t:\t" + PositiveCore);
                PositiveCoreSum.addAndGet(PositiveCore);
                PositiveSum.addAndGet(Positive);

                Double NeutralCore = sm.get(2);
                Double Neutral = (double) Math.round(NeutralCore * 100d);
//                System.out.println("Neutral:\t" + Neutral + "\t:\t" + NeutralCore);
                NeutralCoreSum.addAndGet(NeutralCore);
                NeutralSum.addAndGet(Neutral);

                Double NegativeCore = sm.get(1);
                Double Negative = (double) Math.round(NegativeCore * 100d);
//                System.out.println("Negative:\t" + Negative + "\t:\t" + NegativeCore);
                NegativeCoreSum.addAndGet(NegativeCore);
                NegativeSum.addAndGet(Negative);

                Double VeryNegativeCore = sm.get(0);
                Double VeryNegative = (double) Math.round(VeryNegativeCore * 100d);
//                System.out.println("VeryNegative:\t" + VeryNegative + "\t:\t" + VeryNegativeCore);
                VeryNegativeCoreSum.addAndGet(VeryNegativeCore);
                VeryNegativeSum.addAndGet(VeryNegative);

//                System.out.println("SentimentScore:"+ RNNCoreAnnotations.getPredictedClass(tree));
//                System.out.println("SentimentType:" + sentimentType);
            }
//            System.out.println("-------------");
        });

        System.out.println("VeryPositiveCoreSum:\t" + VeryPositiveCoreSum.get() + "\tVeryPositiveSum:\t" + VeryPositiveSum.get());
        System.out.println("PositiveCoreSum:\t" + PositiveCoreSum.get() + "\tPositiveSum:\t" + PositiveSum.get());
        System.out.println("NeutralCoreSum:\t" + NeutralCoreSum.get() + "\tNeutralSum:\t" + NeutralSum.get());
        System.out.println("NegativeCoreSum:\t" + NegativeCoreSum.get() + "\tNegativeSum:\t" + NegativeSum.get());
        System.out.println("VeryNegativeCoreSum:\t" + VeryNegativeCoreSum.get() + "\tVeryNegativeSum:\t" + VeryNegativeSum.get());


        Double coreSum = VeryPositiveCoreSum.get() * 10 + PositiveCoreSum.get() * 5 + NeutralCoreSum.get() * 1 + NegativeCoreSum.get() * (-5) + VeryNegativeCoreSum.get() * (-10);
        System.out.println("Core Sum:" + coreSum);

        Double sum = VeryPositiveSum.get() * 10 + PositiveSum.get() * 5 + NeutralSum.get() * 1 + NegativeSum.get() * (-5) + VeryNegativeSum.get() * (-10);
        System.out.println("Sum:" + sum);
    }
}
