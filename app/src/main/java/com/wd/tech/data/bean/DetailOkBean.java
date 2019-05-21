package com.wd.tech.data.bean;

import java.util.List;

public class DetailOkBean {


    /**
     * result : {"comment":14,"content":"<p>时至今日，加密货币与智能合约平台当中开始出现越来越多安全漏洞，而其中部分漏洞甚至源自这些平台的构建基础。<\/p>\r\n<p style=\"text-align: center;\"><a class=\"fancybox_content\" href=\"http://img.zhiding.cn/5/438/liWMCBxLdCi6.jpg?rand=94\"><img src=\"http://img.zhiding.cn/5/438/liWMCBxLdCi6_600.jpg?rand=94\" alt=\"威胁不止有51%攻击，区块链为何频遭黑客入侵？\"><\/a><\/p>\r\n<p>就在上月月初，Coinbase公司的安全团队注意到以太坊经典(Ethereum Classic)当中出现了一些异常迹象。顺带一提，Coinbase所提供的高人气加密货币交易平台正是以以太坊经典为基础。事实上，所谓异常并不足以说明问题的严重性\u2014\u2014整个区块链，包括其中的全部交易历史，尽皆遭到入侵。<\/p>\r\n<p>攻击者以某种方式控制了超过半数的区块链网络算力，并借此对交易历史进行了重写。这意味着其完全有能力多次使用相同的加密货币\u2014\u2014也就是所谓\u201c重复花费\u201d。截至攻击者被发现之时，其滥用的资金总额高达110万美元。不过Coinbase方面宣称，此次事件并没有造成任何实际性的账户货币失窃。然而，第二大高人气交易所Gate.io就没这么幸运了，问题出现之后攻击者共造成了约20万美元的损失(但奇怪的是，几天之后其中有半数资金得到了恢复)。<\/p>\r\n<p>就在一年之前，上述噩梦般的场景似乎还只存在于理论之中。但此番针对以太坊经典网络的51%攻击只是近期一系列指向区块链平台的攻击活动中的一例，而这些问题进一步加剧了加密货币这一新兴行业的整体波动。<\/p>\r\n<p>单从已经公开披露的情况来看，自2017年年初开始，黑客就已经总计窃取到价值近20亿美元的加密货币，而且其中大部分来自交易所。如今，受到巨大利益吸引的已经不再是单枪匹马的独行劫匪，而是规模更大且更为复杂的网络犯罪团伙。分析企业Chainalysis公司最近表示，单单是两个最为活跃的黑客团队就有可能已经从交易所处偷走了10亿美元。<\/p>\r\n<p>对于这样的状况，大家没必要过度惊讶。实际上，区块链对于盗窃分子一直有着极高的吸引力。这一方面是因为区块链上的欺诈交易无法逆转，这与传统金融体系的情况完全不同。除此之外，正如大家所知，区块链既拥有着独特的安全功能，同时也有着特殊的安全漏洞。<\/p>\r\n<p>自从十年前比特币正式诞生以来，我们已经至少在理论上理解到了这一点。但在过去一年中，新的加密货币项目就如寒武纪生命大爆发一样争相涌现，而我们也已经在实践层面体会到了由此带来的影响，甚至是此类固有缺失可能给区块链及数字资产的未来造成的后续冲击。<\/p>\r\n<p><strong>如何入侵区块链?<\/strong><\/p>\r\n<p>在继续讲解之前，让我们首先聊聊区块链的本质。<\/p>\r\n<p>区块链实际上就是一套由整体计算机网络所维护的加密数据库，其中每一台计算机都存储着最新的数据副本。区块链协议则代表一组规则，用于规定网络当中的各台计算机(被称为节点)应如何验证新交易并将其添加至数据库当中。这项协议采用密码学、博弈论以及经济学等手段激励各节点努力保护网络，而非出于个人利益而实施网络攻击。如果设置得当，那么这套系统有望极大程度提升添加错误交易的难度与成本，同时以更理想的效率对有效交易进行验证。<\/p>\r\n<p>正是凭借着这一核心特性，才让区块链技术在众多行业当中具有着独特的吸引力，而金融行业正是最直接的受众。富达投资与纽约证券交易所的持有方洲际交易所等知名机构已经在着手推出新的服务，计划将区块链技术融入现有金融体系当中。甚至多个国家的中央银行也在考虑借此发行数字形式的全新本国货币。<\/p>\r\n<p>然而，区块链系统越复杂，出现错误的可能性也就越高。本月早些时候，负责Zcash币(一种利用极度复杂的数学方法帮助用户个人之间进行交易的加密货币)的公司指出，其已经悄悄修复了协议当中偶然出现的一项\u201c微妙的加密漏洞\u201d。攻击者可以借此伪造出无穷无尽的Zcash币。幸运的是，在修复之前该漏洞似乎并没有被实际利用。<\/p>\r\n<p>协议并不是唯一需要严格保护的对象。要进行加密货币交易或者节点运行，大家必须使用软件客户端，而客户端当中也有可能包含漏洞。去年9月，比特币主要客户端Bitcoin Core被曝出存在一项严重bug(同样没有对外公布)，其有可能令攻击者获得比系统设计量更高的比特币产出。可以想见，相关开发者以近乎疯狂的方式将其快速修复掉了。<\/p>\r\n<p>尽管如此，近期大部分最引人注目的黑客攻击并非针对区块链网络本体的入侵，而是将矛头指向了交易所\u2014\u2014即人们购买、交易以及持有加密货币的网站。更可怕的是，很多盗窃案之所以能够得手，完全是由于这些交易所在基础安全措施方面非常糟糕。不过今年1月的事件有所不同，以太坊经典面对的是51%攻击。<\/p>\r\n<p><strong>51%原则<\/strong><\/p>\r\n<p>事实上，大多数加密货币对于51%攻击都有着极高的敏感性。这是因为大多数加密货币项目都基于区块链，这些区块链所使用的工作证明机制正是交易验证的核心机制。在这一过程(也被称为挖矿)中，节点会花费大量算力来证明自身足够可信，从而将关于新交易的信息添加到数据库之内。以此为前提，通过某种方式控制大部分网络采矿能力的矿工即可向这些规模可观的节点发送付款交易，从而欺骗其他用户并创建出现有区块链的新版本\u2014\u2014在这套版本中，该付款操作从未发生。这样的新版本被称为分叉。更重要的是，控制大部分挖矿能力的攻击者完全能够让这个分叉被视为区块链的权威版本，从而继续消费同一批加密货币。<\/p>\r\n<p>对于高人气区块链而言，这种盗窃方式可能会带来极高的成本。根据Crypto51网站的统计，租用采矿权足以被用于攻击比特币区块链的设备，目前的单小时成本已经超过26万美元。但如果着眼于比特币之后的1500多种加密货币，我们会发现其网络规模明显小得多，因此需要控制的算力自然也就少得多。加密货币价格下跌导致这些货币价值缩水，这又进一步促使矿工们退出网络，最终形成了网络保护能力持续弱化的恶性循环。<\/p>\r\n<p>2018年年中，攻击者开始针对一系列体量较小、交易活跃度较低的加密货币(包括Verge币、Monacoin以及Bitcoin Cold)进行51%攻击，并总共盗窃到大约2000万美元。去年秋季，黑客们又通过一系列针对Vertcoin加密货币的攻击偷走了大约10万美元。此次针对以太坊经典的攻击共涉及超过100万美元，这也是人气排名前二十位的货币首次遭遇正面入侵。<\/p>\r\n<p>基于区块链型文件存储平台Sia公司联合创始人David Vorick预测称，未来51%攻击活动将在频率与严重程度方面持续升级，而且交易所将首当其冲受到重复花费问题的损害。他同时表示，推动这一趋势的重大事件，就是所谓hashrate市场的兴起。攻击者可以通过这些市场租赁到可观的算力并用于攻击。Vorick在以太坊经典遭遇黑客攻击后写道，\u201c在决定支持哪种加密货币时，交易所显然需要做出更为严格的限制与谨慎的判断。\u201d<\/p>\r\n<p><strong>一大堆全新的蠕虫病毒<\/strong><\/p>\r\n<p>除了51%攻击之外，区块链安全漏洞中还存在着一类全新分支，而研究人员们也刚刚开始对其进行探索\u2014\u2014这就是智能合约漏洞。巧合的是，以太坊经典、特别是其背后的设计思维，正是理解智能合约漏洞的绝佳切入点。<\/p>\r\n<p>所谓智能合约，属于在区块链网络之上运行的计算机程序。它可以根据预先制定的规则与条件自动完成加密货币的转移。智能合约有着很多潜在用途，例如支持真实法律合约或者复杂的金融交易等等。而其另一个重要用途，同时也是我们感兴趣的方向，就是建立起一种投票机制。通过这种投票机制，风险投资基金的所有投资者都能够参与进来，共同决定资金的具体分配方式。<\/p>\r\n<p>2016年，名为去中心化自治组织(简称DAO)的基金正式建立，其实现基础正是以太坊区块链。此后不久，一名攻击者通过利用DAO智能合约管理系统中存在的某一无法预料的缺陷，偷走了总价值超过6000万美元的加密货币。从本质上讲，这项缺陷允许黑客在系统未将注册资金识别为已撤回的前提下继续向帐户申请资金。<\/p>\r\n<p>实时智能合约中的bug可以引发一种独特的高危状况。在传统软件中，我们可以利用补丁修复此类bug。但在区块链世界中，问题绝对没那么简单。以太坊苏格兰分部研究科学家兼智能合约安全初创企业ChainSecurity公司联合创始人Petar Tsankov指出，由于区块链上的交易无法被撤销，因智能合约的部署更像是发射火箭\u2014\u2014\u201c软件绝对不可以出错。\u201d<\/p>\r\n<p>当然，也存在着某些修复机制。虽然无法彻底解决问题，但研究人员可以通过部署其它智能合约并与原有合约进行交互，从而对后者进行\u201c升级\u201d。另外，开发人员还可以在网络当中构建集中式交易终止机制，从而在检测到黑客攻击之后停止一切交易活动。然而对于资金已经遭到盗窃的用户来说，一切都已经无法挽回。<\/p>\r\n<p>实际上，恢复被盗资金的唯一方式就是重写历史记录\u2014\u2014即将区块链回滚至攻击发生之前的某一时间点，建立新的区块链分叉，并要求网络上的每一位用户接受这一新的区块链版本。当时，以太坊的开发者们就做出了这样的决定。大部分(但并非全部)社区成员转向了新链，也就是我们现在所说的以太坊。但仍有一小部分坚持继续使用原始链，而这就构成了以太坊经典。<\/p>\r\n<p>上个月，Tsankov在ChainSecurity的团队成功使以太坊免于重复DAO的灾难。在某项策划许久的重大软件升级正式上线的前一天，该公司提醒以太坊的核心开发人员，这次升级有可能引发意想不到的后果\u2014\u2014即在区块链上遗留一些合约，其很容易受到导致DAO攻击的同类漏洞的影响。开发人员迅速推迟了升级进程，并决定在西服晚些时候再行推出。<\/p>\r\n<p>然而，区块链安全企业AnChain.ai公司联合创始人兼CEO Victor Fang表示，数百份颇具价值的以太坊智能合约仍面临着这种所谓\u201c重入bug\u201d的威胁。根据去年进行的一项研究，成千上万份智能合约中可能包含着大量其它漏洞。公链的本质决定着，如果智能合约当中存在bug，那么黑客必然能够将其发现\u2014\u2014因为公链合约的源代码通常会公开发布。曾效力于网络安全厂商FireEye公司的Fang指出，\u201c这与传统的安全实现思路存在很大差别。\u201d<\/p>\r\n<p>存在bug的合约，特别是那些涉及数十万甚至数百万美元的合约，吸引到了那批以往关注银行或者政府机构的高水平黑客。去年8月，AnChain确定了五个以太坊地址，它们都参与到了一起极为复杂的攻击活动当中。通过这次攻击，黑客凭借某高人气赌博游戏合约内的缺陷窃取到总计400万美元。<\/p>\r\n<p><strong>那么，我们能够成功抵御这些黑客吗?<\/strong><\/p>\r\n<p>AnChain.ai公司正是近来为了解决区块链黑客威胁而建立的几家初创企业之一。其希望利用人工智能技术监控交易并检测可疑活动，同时扫描智能合约代码以确定其中的已知漏洞。<\/p>\r\n<p>包括Tsankov的ChainSecurity在内的其它一些公司，则在着手开发基于所谓形式验证这一计算机科学技术的新型审计服务。其目标在于立足数学层面证明合约代码能够切实满足创建者的规则意图。Tsankov解释称，这些审计工具在过去一年中才刚刚出现，这使得智能合约创建者得以提前消除诸多\u201c低级\u201d错误。然而，整个审计过程仍然既昂贵又耗时。<\/p>\r\n<p>康奈尔大学隐形货币与合约倡议研究员Philip Daian表示，行业也可以尝试使用额外的智能合约以建立针对区块链项目的\u201cbug赏金计划\u201d，即鼓励人们报告缺陷以换取加密货币奖励。<\/p>\r\n<p>但确保代码清洁正确的努力终究是有上限的。毕竟区块链代表着一类复杂的经济系统，其取决于人类不可预测的行为，而人总能够找到新的突破口。举例来说，Daian和他的同事已经展示了攻击者能够如何通过各类高人气游戏的以太坊智能合约为自己牟利。<\/p>\r\n<p>简而言之，尽管区块链技术长期以来一直因天然具有安全优势而受到人们的欢迎，但在某些条件下其也可能非常脆弱。有时候这些问题源自攻击者的恶意计划，有时候也单纯因为软件在无意中发生了错误。而在其它情况下，这更像是一种灰色区域\u2014\u2014即复杂的代码、区块链经济学观以及人类的贪婪相互杂糅而形成的共同责任。自从这项技术诞生以来，上述问题的存在也早已在理论当中得到了体现。而如今，世界上已经出现了这么多区块链项目，而我们正不得不以极高的代价探索它的真实意义。<\/p>\r\n","id":60,"informationList":[{"id":58,"thumbnail":"http://img.zhiding.cn/5/642/liZsoBMTFzZoU.jpg?rand=140","title":"5G元年话5G：让生活再快一些"},{"id":56,"thumbnail":"http://img.zhiding.cn/5/966/liXq33UXpxgo6.jpg?rand=43","title":"谷歌踏入区块链搜索 触及八大最活跃网络完整数据集"}],"integralCost":0,"plate":[],"praise":7,"readPower":1,"relatedArticles":"58,56","releaseTime":1553063676000,"share":4,"source":"侏罗纪","summary":"时至今日，加密货币与智能合约平台当中开始出现越来越多安全漏洞，而其中部分漏洞甚至源自这些平台的构建基础。","thumbnail":"http://img.zhiding.cn/5/438/liWMCBxLdCi6.jpg?rand=94","title":"威胁不止有51%攻击，区块链为何频遭黑客入侵？","whetherCollection":2,"whetherGreat":2,"yuanCost":0}
     * message : 查询成功
     * status : 0000
     */

    private ResultBean result;
    private String message;
    private String status;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static class ResultBean {
        /**
         * comment : 14
         * content : <p>时至今日，加密货币与智能合约平台当中开始出现越来越多安全漏洞，而其中部分漏洞甚至源自这些平台的构建基础。</p>
         <p style="text-align: center;"><a class="fancybox_content" href="http://img.zhiding.cn/5/438/liWMCBxLdCi6.jpg?rand=94"><img src="http://img.zhiding.cn/5/438/liWMCBxLdCi6_600.jpg?rand=94" alt="威胁不止有51%攻击，区块链为何频遭黑客入侵？"></a></p>
         <p>就在上月月初，Coinbase公司的安全团队注意到以太坊经典(Ethereum Classic)当中出现了一些异常迹象。顺带一提，Coinbase所提供的高人气加密货币交易平台正是以以太坊经典为基础。事实上，所谓异常并不足以说明问题的严重性——整个区块链，包括其中的全部交易历史，尽皆遭到入侵。</p>
         <p>攻击者以某种方式控制了超过半数的区块链网络算力，并借此对交易历史进行了重写。这意味着其完全有能力多次使用相同的加密货币——也就是所谓“重复花费”。截至攻击者被发现之时，其滥用的资金总额高达110万美元。不过Coinbase方面宣称，此次事件并没有造成任何实际性的账户货币失窃。然而，第二大高人气交易所Gate.io就没这么幸运了，问题出现之后攻击者共造成了约20万美元的损失(但奇怪的是，几天之后其中有半数资金得到了恢复)。</p>
         <p>就在一年之前，上述噩梦般的场景似乎还只存在于理论之中。但此番针对以太坊经典网络的51%攻击只是近期一系列指向区块链平台的攻击活动中的一例，而这些问题进一步加剧了加密货币这一新兴行业的整体波动。</p>
         <p>单从已经公开披露的情况来看，自2017年年初开始，黑客就已经总计窃取到价值近20亿美元的加密货币，而且其中大部分来自交易所。如今，受到巨大利益吸引的已经不再是单枪匹马的独行劫匪，而是规模更大且更为复杂的网络犯罪团伙。分析企业Chainalysis公司最近表示，单单是两个最为活跃的黑客团队就有可能已经从交易所处偷走了10亿美元。</p>
         <p>对于这样的状况，大家没必要过度惊讶。实际上，区块链对于盗窃分子一直有着极高的吸引力。这一方面是因为区块链上的欺诈交易无法逆转，这与传统金融体系的情况完全不同。除此之外，正如大家所知，区块链既拥有着独特的安全功能，同时也有着特殊的安全漏洞。</p>
         <p>自从十年前比特币正式诞生以来，我们已经至少在理论上理解到了这一点。但在过去一年中，新的加密货币项目就如寒武纪生命大爆发一样争相涌现，而我们也已经在实践层面体会到了由此带来的影响，甚至是此类固有缺失可能给区块链及数字资产的未来造成的后续冲击。</p>
         <p><strong>如何入侵区块链?</strong></p>
         <p>在继续讲解之前，让我们首先聊聊区块链的本质。</p>
         <p>区块链实际上就是一套由整体计算机网络所维护的加密数据库，其中每一台计算机都存储着最新的数据副本。区块链协议则代表一组规则，用于规定网络当中的各台计算机(被称为节点)应如何验证新交易并将其添加至数据库当中。这项协议采用密码学、博弈论以及经济学等手段激励各节点努力保护网络，而非出于个人利益而实施网络攻击。如果设置得当，那么这套系统有望极大程度提升添加错误交易的难度与成本，同时以更理想的效率对有效交易进行验证。</p>
         <p>正是凭借着这一核心特性，才让区块链技术在众多行业当中具有着独特的吸引力，而金融行业正是最直接的受众。富达投资与纽约证券交易所的持有方洲际交易所等知名机构已经在着手推出新的服务，计划将区块链技术融入现有金融体系当中。甚至多个国家的中央银行也在考虑借此发行数字形式的全新本国货币。</p>
         <p>然而，区块链系统越复杂，出现错误的可能性也就越高。本月早些时候，负责Zcash币(一种利用极度复杂的数学方法帮助用户个人之间进行交易的加密货币)的公司指出，其已经悄悄修复了协议当中偶然出现的一项“微妙的加密漏洞”。攻击者可以借此伪造出无穷无尽的Zcash币。幸运的是，在修复之前该漏洞似乎并没有被实际利用。</p>
         <p>协议并不是唯一需要严格保护的对象。要进行加密货币交易或者节点运行，大家必须使用软件客户端，而客户端当中也有可能包含漏洞。去年9月，比特币主要客户端Bitcoin Core被曝出存在一项严重bug(同样没有对外公布)，其有可能令攻击者获得比系统设计量更高的比特币产出。可以想见，相关开发者以近乎疯狂的方式将其快速修复掉了。</p>
         <p>尽管如此，近期大部分最引人注目的黑客攻击并非针对区块链网络本体的入侵，而是将矛头指向了交易所——即人们购买、交易以及持有加密货币的网站。更可怕的是，很多盗窃案之所以能够得手，完全是由于这些交易所在基础安全措施方面非常糟糕。不过今年1月的事件有所不同，以太坊经典面对的是51%攻击。</p>
         <p><strong>51%原则</strong></p>
         <p>事实上，大多数加密货币对于51%攻击都有着极高的敏感性。这是因为大多数加密货币项目都基于区块链，这些区块链所使用的工作证明机制正是交易验证的核心机制。在这一过程(也被称为挖矿)中，节点会花费大量算力来证明自身足够可信，从而将关于新交易的信息添加到数据库之内。以此为前提，通过某种方式控制大部分网络采矿能力的矿工即可向这些规模可观的节点发送付款交易，从而欺骗其他用户并创建出现有区块链的新版本——在这套版本中，该付款操作从未发生。这样的新版本被称为分叉。更重要的是，控制大部分挖矿能力的攻击者完全能够让这个分叉被视为区块链的权威版本，从而继续消费同一批加密货币。</p>
         <p>对于高人气区块链而言，这种盗窃方式可能会带来极高的成本。根据Crypto51网站的统计，租用采矿权足以被用于攻击比特币区块链的设备，目前的单小时成本已经超过26万美元。但如果着眼于比特币之后的1500多种加密货币，我们会发现其网络规模明显小得多，因此需要控制的算力自然也就少得多。加密货币价格下跌导致这些货币价值缩水，这又进一步促使矿工们退出网络，最终形成了网络保护能力持续弱化的恶性循环。</p>
         <p>2018年年中，攻击者开始针对一系列体量较小、交易活跃度较低的加密货币(包括Verge币、Monacoin以及Bitcoin Cold)进行51%攻击，并总共盗窃到大约2000万美元。去年秋季，黑客们又通过一系列针对Vertcoin加密货币的攻击偷走了大约10万美元。此次针对以太坊经典的攻击共涉及超过100万美元，这也是人气排名前二十位的货币首次遭遇正面入侵。</p>
         <p>基于区块链型文件存储平台Sia公司联合创始人David Vorick预测称，未来51%攻击活动将在频率与严重程度方面持续升级，而且交易所将首当其冲受到重复花费问题的损害。他同时表示，推动这一趋势的重大事件，就是所谓hashrate市场的兴起。攻击者可以通过这些市场租赁到可观的算力并用于攻击。Vorick在以太坊经典遭遇黑客攻击后写道，“在决定支持哪种加密货币时，交易所显然需要做出更为严格的限制与谨慎的判断。”</p>
         <p><strong>一大堆全新的蠕虫病毒</strong></p>
         <p>除了51%攻击之外，区块链安全漏洞中还存在着一类全新分支，而研究人员们也刚刚开始对其进行探索——这就是智能合约漏洞。巧合的是，以太坊经典、特别是其背后的设计思维，正是理解智能合约漏洞的绝佳切入点。</p>
         <p>所谓智能合约，属于在区块链网络之上运行的计算机程序。它可以根据预先制定的规则与条件自动完成加密货币的转移。智能合约有着很多潜在用途，例如支持真实法律合约或者复杂的金融交易等等。而其另一个重要用途，同时也是我们感兴趣的方向，就是建立起一种投票机制。通过这种投票机制，风险投资基金的所有投资者都能够参与进来，共同决定资金的具体分配方式。</p>
         <p>2016年，名为去中心化自治组织(简称DAO)的基金正式建立，其实现基础正是以太坊区块链。此后不久，一名攻击者通过利用DAO智能合约管理系统中存在的某一无法预料的缺陷，偷走了总价值超过6000万美元的加密货币。从本质上讲，这项缺陷允许黑客在系统未将注册资金识别为已撤回的前提下继续向帐户申请资金。</p>
         <p>实时智能合约中的bug可以引发一种独特的高危状况。在传统软件中，我们可以利用补丁修复此类bug。但在区块链世界中，问题绝对没那么简单。以太坊苏格兰分部研究科学家兼智能合约安全初创企业ChainSecurity公司联合创始人Petar Tsankov指出，由于区块链上的交易无法被撤销，因智能合约的部署更像是发射火箭——“软件绝对不可以出错。”</p>
         <p>当然，也存在着某些修复机制。虽然无法彻底解决问题，但研究人员可以通过部署其它智能合约并与原有合约进行交互，从而对后者进行“升级”。另外，开发人员还可以在网络当中构建集中式交易终止机制，从而在检测到黑客攻击之后停止一切交易活动。然而对于资金已经遭到盗窃的用户来说，一切都已经无法挽回。</p>
         <p>实际上，恢复被盗资金的唯一方式就是重写历史记录——即将区块链回滚至攻击发生之前的某一时间点，建立新的区块链分叉，并要求网络上的每一位用户接受这一新的区块链版本。当时，以太坊的开发者们就做出了这样的决定。大部分(但并非全部)社区成员转向了新链，也就是我们现在所说的以太坊。但仍有一小部分坚持继续使用原始链，而这就构成了以太坊经典。</p>
         <p>上个月，Tsankov在ChainSecurity的团队成功使以太坊免于重复DAO的灾难。在某项策划许久的重大软件升级正式上线的前一天，该公司提醒以太坊的核心开发人员，这次升级有可能引发意想不到的后果——即在区块链上遗留一些合约，其很容易受到导致DAO攻击的同类漏洞的影响。开发人员迅速推迟了升级进程，并决定在西服晚些时候再行推出。</p>
         <p>然而，区块链安全企业AnChain.ai公司联合创始人兼CEO Victor Fang表示，数百份颇具价值的以太坊智能合约仍面临着这种所谓“重入bug”的威胁。根据去年进行的一项研究，成千上万份智能合约中可能包含着大量其它漏洞。公链的本质决定着，如果智能合约当中存在bug，那么黑客必然能够将其发现——因为公链合约的源代码通常会公开发布。曾效力于网络安全厂商FireEye公司的Fang指出，“这与传统的安全实现思路存在很大差别。”</p>
         <p>存在bug的合约，特别是那些涉及数十万甚至数百万美元的合约，吸引到了那批以往关注银行或者政府机构的高水平黑客。去年8月，AnChain确定了五个以太坊地址，它们都参与到了一起极为复杂的攻击活动当中。通过这次攻击，黑客凭借某高人气赌博游戏合约内的缺陷窃取到总计400万美元。</p>
         <p><strong>那么，我们能够成功抵御这些黑客吗?</strong></p>
         <p>AnChain.ai公司正是近来为了解决区块链黑客威胁而建立的几家初创企业之一。其希望利用人工智能技术监控交易并检测可疑活动，同时扫描智能合约代码以确定其中的已知漏洞。</p>
         <p>包括Tsankov的ChainSecurity在内的其它一些公司，则在着手开发基于所谓形式验证这一计算机科学技术的新型审计服务。其目标在于立足数学层面证明合约代码能够切实满足创建者的规则意图。Tsankov解释称，这些审计工具在过去一年中才刚刚出现，这使得智能合约创建者得以提前消除诸多“低级”错误。然而，整个审计过程仍然既昂贵又耗时。</p>
         <p>康奈尔大学隐形货币与合约倡议研究员Philip Daian表示，行业也可以尝试使用额外的智能合约以建立针对区块链项目的“bug赏金计划”，即鼓励人们报告缺陷以换取加密货币奖励。</p>
         <p>但确保代码清洁正确的努力终究是有上限的。毕竟区块链代表着一类复杂的经济系统，其取决于人类不可预测的行为，而人总能够找到新的突破口。举例来说，Daian和他的同事已经展示了攻击者能够如何通过各类高人气游戏的以太坊智能合约为自己牟利。</p>
         <p>简而言之，尽管区块链技术长期以来一直因天然具有安全优势而受到人们的欢迎，但在某些条件下其也可能非常脆弱。有时候这些问题源自攻击者的恶意计划，有时候也单纯因为软件在无意中发生了错误。而在其它情况下，这更像是一种灰色区域——即复杂的代码、区块链经济学观以及人类的贪婪相互杂糅而形成的共同责任。自从这项技术诞生以来，上述问题的存在也早已在理论当中得到了体现。而如今，世界上已经出现了这么多区块链项目，而我们正不得不以极高的代价探索它的真实意义。</p>
         * id : 60
         * informationList : [{"id":58,"thumbnail":"http://img.zhiding.cn/5/642/liZsoBMTFzZoU.jpg?rand=140","title":"5G元年话5G：让生活再快一些"},{"id":56,"thumbnail":"http://img.zhiding.cn/5/966/liXq33UXpxgo6.jpg?rand=43","title":"谷歌踏入区块链搜索 触及八大最活跃网络完整数据集"}]
         * integralCost : 0
         * plate : []
         * praise : 7
         * readPower : 1
         * relatedArticles : 58,56
         * releaseTime : 1553063676000
         * share : 4
         * source : 侏罗纪
         * summary : 时至今日，加密货币与智能合约平台当中开始出现越来越多安全漏洞，而其中部分漏洞甚至源自这些平台的构建基础。
         * thumbnail : http://img.zhiding.cn/5/438/liWMCBxLdCi6.jpg?rand=94
         * title : 威胁不止有51%攻击，区块链为何频遭黑客入侵？
         * whetherCollection : 2
         * whetherGreat : 2
         * yuanCost : 0
         */

        private int comment;
        private String content;
        private int id;
        private int integralCost;
        private int praise;
        private int readPower;
        private String relatedArticles;
        private long releaseTime;
        private int share;
        private String source;
        private String summary;
        private String thumbnail;
        private String title;
        private int whetherCollection;
        private int whetherGreat;
        private int yuanCost;
        private List<InformationListBean> informationList;
        private List<?> plate;

        public int getComment() {
            return comment;
        }

        public void setComment(int comment) {
            this.comment = comment;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getIntegralCost() {
            return integralCost;
        }

        public void setIntegralCost(int integralCost) {
            this.integralCost = integralCost;
        }

        public int getPraise() {
            return praise;
        }

        public void setPraise(int praise) {
            this.praise = praise;
        }

        public int getReadPower() {
            return readPower;
        }

        public void setReadPower(int readPower) {
            this.readPower = readPower;
        }

        public String getRelatedArticles() {
            return relatedArticles;
        }

        public void setRelatedArticles(String relatedArticles) {
            this.relatedArticles = relatedArticles;
        }

        public long getReleaseTime() {
            return releaseTime;
        }

        public void setReleaseTime(long releaseTime) {
            this.releaseTime = releaseTime;
        }

        public int getShare() {
            return share;
        }

        public void setShare(int share) {
            this.share = share;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public String getThumbnail() {
            return thumbnail;
        }

        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getWhetherCollection() {
            return whetherCollection;
        }

        public void setWhetherCollection(int whetherCollection) {
            this.whetherCollection = whetherCollection;
        }

        public int getWhetherGreat() {
            return whetherGreat;
        }

        public void setWhetherGreat(int whetherGreat) {
            this.whetherGreat = whetherGreat;
        }

        public int getYuanCost() {
            return yuanCost;
        }

        public void setYuanCost(int yuanCost) {
            this.yuanCost = yuanCost;
        }

        public List<InformationListBean> getInformationList() {
            return informationList;
        }

        public void setInformationList(List<InformationListBean> informationList) {
            this.informationList = informationList;
        }

        public List<?> getPlate() {
            return plate;
        }

        public void setPlate(List<?> plate) {
            this.plate = plate;
        }

        public static class InformationListBean {
            /**
             * id : 58
             * thumbnail : http://img.zhiding.cn/5/642/liZsoBMTFzZoU.jpg?rand=140
             * title : 5G元年话5G：让生活再快一些
             */

            private int id;
            private String thumbnail;
            private String title;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getThumbnail() {
                return thumbnail;
            }

            public void setThumbnail(String thumbnail) {
                this.thumbnail = thumbnail;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }
        }
    }
}
