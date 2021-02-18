package mj_fb;

public class FB_Design {

    /**
     * https://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=713872&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3089%5D%5Bvalue%5D%5B3%5D%3D3%26searchoption%5B3089%5D%5Btype%5D%3Dcheckbox%26searchoption%5B3046%5D%5Bvalue%5D%3D2%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311%26orderby%3Ddateline
     *
     * 这段时间面试了脸书的码工职位，整理了一下最近地里和朋友那里打听出来的系统设计题目，分享出来换大米，换大米~~~
     * - Push notification
     * - Search status，或者叫twitter search，一般要求real time，仅限text post。可以参考 https://blog.twitter.com/enginee ... rch-experience.html
     * - Aggregation system，一般会考虑到fast和slow两种cases
     *
     * - Design Yelp，经典题目，quadtree或者grid，geohash我自己没多看，觉着重点不在这里
     *
     * - Translation syste，两种思路，一个是google translate这种，你可以assume已经有一个现成可用的translation service，然后你要设计一个系统满足三高。另外一个思路可以借鉴一下airbnb的翻译系统 https://medium.com/airbnb-engine ... atform-45cf0104b63c
     *
     * - News feed
     *
     * - Design Netflix
     *
     * - i18n，参见上面说的airbnb的翻译系统
     *
     * - Collaborative doc editing，就是设计个google doc
     *
     * - Subscription system，比如说youtube的subscription
     *
     * - Hashtag trend，类似于topK，YouTube上有个视频讲的挺好 https://www.youtube.com/watch?v=kx-XDoPjoHw&t=53s
     *
     * ，另外我也很推荐这个哥们儿的channel
     *
     * - Live commenting system，个人感觉这个地方偏重考database
     *
     * - KV store，经典题，主要靠怎么满足三高
     *
     * - Design Facebook Messenge，要求能做到group chat
     *
     * - Design Instagram
     *
     * - Proximity server backend，参考design Yelp
     *
     * - Design load balancer，要求包含balance servers的workload的功能
     *
     * - Ad click counter，参考前面的hashtag trend，只是有相似之处并不完全相同，考虑slow和fast两种实现可能都需要
     *
     * - Web crawler，看到大家提到的都是需要跑在botnet上，我自己能想到的就是中控server负责存储、判重，还有负责给bot们发命令，命令里面包括url。Bots们接收命令，下载网页，解析文字和urls，然后把网页文字内容和URLs发回给中控server。另外中控server要能做到三高。
     *
     * - Design typeahead suggestions，也就是autocomplete，经典题
     *
     * - Design privacy settings at Facebook，几个privacy类型，比如说public可见，只能朋友看，只能朋友和朋友的朋友看，只能自己看
     *
     * 我个人的经验是45~60分钟不可能回答到完美，只能尽量做到战前做好准备工作，正所谓凡事预则立不预则废，尽人事听天命而已。实战中需要注意的一点是把握好时间和节奏，如果一个面试官不断的打断你打乱你的节奏，只能尽力往回带了不然会漏掉该讲出来的东西，总之不要给这种面试官机会质疑你不会这个不知道那个。
     * 简而言之，自己盯着点时间，别说的自己都搂不住了用光了时间：
     * - clarification: 5 min
     * - high level: 15 min，给出一个大体结构，然后做data volume的估计，然后从最需要改进的地方开始deep dive
     * - deep dive：剩下的时间就全是这一块儿了，包括你自己的深入解释和回答interviewer的问题。
     * 最后这一部分interviewer问的问题一定要说清楚，觉着有不懂的不要瞎说，毕竟是模拟一个工作环境。他不问的情况下你要知道下一步该往哪里走，多多交流总是没错的，随时问问牛逼你觉着我这样做一下怎么样啊？我如果下一步专注这部分你开心不？到现在为止有啥问题或者concern没有啊？这些问题也随时问出来显得我们真的很想让interviewer加入讨论，总之interviewer爽了你才会爽。
     *
     * 看到新题再补充吧，另外我买了Alex Xu出的system design Interview，相当入门非常好读。
     */
}
