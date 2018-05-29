package com.trip.animaljie.makeinabyss;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.loopeer.cardstack.AllMoveDownAnimatorAdapter;
import com.loopeer.cardstack.CardStackView;
import com.loopeer.cardstack.UpDownAnimatorAdapter;
import com.loopeer.cardstack.UpDownStackAnimatorAdapter;
import com.trip.animaljie.makeinabyss.CardStackViewAdapter.CardViewAdapter;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Arrays;

public class TripActivity extends AppCompatActivity implements CardStackView.ItemExpendListener {
    private CardStackView mStackView;
    private LinearLayout mActionButtonContainer;
    private CardViewAdapter mTestStackAdapter;

    public static Integer[] TEST_DATAS = new Integer[]{
            R.color.color_1,
            R.color.color_2,
            R.color.color_3,
            R.color.color_4,
            R.color.color_5,
            R.color.color_6,
            R.color.color_7,
            R.color.color_8,
            R.color.color_9,
            R.color.color_10,
            R.color.color_11,
            R.color.color_12,
            R.color.color_13,
            R.color.color_14,
            R.color.color_15,
            R.color.color_16,
            R.color.color_17,
            R.color.color_18,
            R.color.color_19,
            R.color.color_20,
            R.color.color_21,
            R.color.color_22,
            R.color.color_23,
            R.color.color_24,
            R.color.color_25,
            R.color.color_26
    };


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_all_down:
                mStackView.setAnimatorAdapter(new AllMoveDownAnimatorAdapter(mStackView));
                break;
            case R.id.menu_up_down:
                mStackView.setAnimatorAdapter(new UpDownAnimatorAdapter(mStackView));
                break;
            case R.id.menu_up_down_stack:
                mStackView.setAnimatorAdapter(new UpDownStackAnimatorAdapter(mStackView));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onPreClick(View view) {
        mStackView.pre();
    }

    public void onNextClick(View view) {
        mStackView.next();
    }

    @Override
    public void onItemExpend(boolean expend) {
        mActionButtonContainer.setVisibility(expend ? View.VISIBLE : View.GONE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip);
        final ArrayList<Integer> card_image = new ArrayList<>();
        card_image.add(R.drawable.city1);
        card_image.add(R.drawable.city2);
        card_image.add(R.drawable.city3);
        card_image.add(R.drawable.city4);
        card_image.add(R.drawable.city5);
        card_image.add(R.drawable.city6);
        card_image.add(R.drawable.city7);
        card_image.add(R.drawable.city8);
        card_image.add(R.drawable.city9);
        card_image.add(R.drawable.city10);
        card_image.add(R.drawable.city11);
        card_image.add(R.drawable.city12);
        card_image.add(R.drawable.city13);
        card_image.add(R.drawable.city14);
        card_image.add(R.drawable.city15);
        card_image.add(R.drawable.city16);
        card_image.add(R.drawable.city17);
        card_image.add(R.drawable.city18);
        card_image.add(R.drawable.city19);
        card_image.add(R.drawable.city20);
        card_image.add(R.drawable.city21);
        card_image.add(R.drawable.city22);
        card_image.add(R.drawable.city23);
        card_image.add(R.drawable.city24);
        card_image.add(R.drawable.city25);
        card_image.add(R.drawable.city26);


        final ArrayList<String> info = new ArrayList<>();
        info.add("青海湖是一具富有神奇色彩的游览地，也是一个为全世界科学家所注目的巨大宝湖。它浩瀚神秘，是大自然赐予青海高原的一面巨大的宝镜，清澈碧蓝的湖面上微微泛动的波澜似乎在悄声述说着一个古老的传说");
        info.add("“中国十大最美海岛”之一海陵岛，在绵延的海岸线上，异彩纷呈地点缀着12处风景各异的天然海滩，滩阔浪柔，水碧沙净，一湾一景，各具特色。如果把海陵岛作为一个小型的世界的沙滩展览馆，绝不为过。");
        info.add("“桂林山水甲天下”，而如诗如画的漓江是桂林山水的重要组成部分。由桂林至阳朔84公里的漓江，像一条青绸绿带，盘绕在万点峰峦之间，奇峰夹岸，碧水萦回，削壁垂河，青山浮水，风光旖旎，犹如一幅百里画卷。千百年来，它不知陶醉了多少文人墨客。");
        info.add("被誉为“天下第一湾”的亚龙湾，拥有近8公里长绵软细腻的沙滩，能见度达10米的清澈海水，保存完好、形态各异、色彩缤纷的海底珊瑚和热带鱼种。这里不仅是着名的滨海浴场，还是中国难得的潜水胜地。");
        info.add("外滩矗立着各种风格迥异的中西建筑，堪称“万国建筑博览”，它是近代上海历史的缩影。放眼望去，可以领略到黄浦江的迷人风采，欣赏陆家嘴日新月异的靓丽新姿。入夜，外滩璀璨夺目，分外迷人。");
        info.add("静思园广百余亩，园中堂构，宏伟轩敞，风雅精致，汲古图新。风亭雨楼，水榭月舫，九曲回廊，斗折蛇行。假山奇石，深容藏幽。湖水淼淼，荷香悠悠。伫鹤亭桥，隔水相望，弘雅堂、兰花厅、嘉会堂、盆景园、米芾拜石，目不暇接，美不胜收。");
        info.add("说到杭州西湖，逶迤群山之间，林泉秀美，溪涧幽深；兼有绿荫环抱，山色葱茏，画桥烟柳，云树笼纱，一番\"水光潋滟晴方好，山色空蒙雨亦奇\"的胜景，难怪诗云\"天下西湖三十六，此中最美是杭州\"。西湖依杭州而名，杭州仗西湖而盛，两者可谓是连理共存。");
        info.add("有“天下第一山”之美誉的五岳之首泰山，数千年来，让十二位帝皇前来封禅，让孔子留下了“登泰山而小天下”的赞叹，让杜甫留下了“会当凌绝顶，一览众山小”的千古绝唱。现在的泰山，继续五岳独尊，成为世界首例自然与文化双遗产，独一无二，无法复制。");
        info.add("黄山集中国各大名山的美景于一身，尤其以奇松、怪石、云海、温泉“四绝”著称，是大自然造化中的奇迹，有“天下第一奇山”之美称。为道教圣地，遗址遗迹众多，传轩辕黄帝曾在此炼丹。徐霞客曾两次游黄山，留下了“五岳归来不看山，黄山归来不看岳”的感叹。");
        info.add("高北土楼群是福建土楼世界文化遗产精华之一，是永定土楼的代表作。景区里有“圆楼之王”美誉的承启楼和历史悠久的五云楼、世泽楼以及有“博士楼”之称的侨福楼等土楼，众土楼和大自然融为一体，呈现一派美妙无比的田园风光。");
        info.add("神农架素有科学迷宫之称，除举世闻名的“野人”之谜外，神奇的白化动物也吸引着科学考察人员和海内外游客。");
        info.add("袁家界位于张家界森林公园北部，是镶嵌在武陵源核心景区的一颗明珠。袁家界风景以雄、奇、险、峻着称，在十多华里的环山游道上，沿途景色美不胜数。");
        info.add("洛都四郊，山水之胜，龙门首焉。”龙门石窟青山绿水，万像生辉，这里有东、西两座青山对峙，伊水缓缓北流。远远望去，犹如一座天然门阙，被誉为洛阳八大景之首，是洛阳最好的风景区。");
        info.add("诗人对庐山瑰丽山水的逸兴壮思，让多少人对庐山心驰神往。庐山，又称“匡山”或“匡庐”，传说殷周时期有匡氏兄弟七人结庐隐居于此，后成仙而去，其所居之庐幻化为山，故而得名。");
        info.add("北京故宫是明、清两代的皇宫，无与伦比的古代建筑杰作，世界现存最大、最完整的木质结构的古建筑群。它是中华民族的骄傲所在，也是全人类的珍贵文化遗产。");
        info.add("避暑山庄是中国著名的古代帝王宫苑，是中国现存最大的古典皇家园林。与北京紫禁城相比，避暑山庄以朴素淡雅的山村野趣为格调，取自然山水之本色，吸收江南塞北之风光，成为中国现存占地最大的古代帝王宫苑。");
        info.add("云冈石窟依山而凿，东西绵延约一公里，在这绵延一公里的石雕群中，雕像大至十几米，小至几公分的石雕，巨石横亘，石雕满目，蔚为大观。他们的形态神彩动人。有的居中正坐，栩栩如生，或击鼓或敲钟，或手捧短笛或载歌载舞，或怀抱琵琶。");
        info.add("碧绿的草原一望无际，悠悠的白云举手可及，成群的牛羊散游草地，弯弯的伊敏河在碧绿无垠的草原上像一条银色的玉带。这里就是巴彦呼硕草原。");
        info.add("从人们发现丽江这块宝地开始，就为这里赋予了种种与爱情有关的定义。这里是爱情的天堂，有促进恋爱激素的神秘空气和土壤，到处都散发着微微香甜的暧昧气。这里是爱情的桃花源，再无趣的人，到了这里，都会被这里的情境所感染，心情变得柔软，生出许多旖旎的情绪。");
        info.add("“捣珠崩玉，飞沫反涌，如烟雾腾空，势甚雄伟，”巨大的黄果树飞瀑，未见其景，先闻其声。近处观瀑，如临万马奔腾之阵；水拍击石，犹似雷劈山崩，令人惊心动魂，不愧享有“中华第一瀑”之盛誉。");
        info.add("巴山蜀水，自古至今，不知迷醉了多少游人！近年来，蜀中胜地又发现一颗五彩的风光“宝石”，这就是被人们称为“神话世界”的九寨沟。");
        info.add("禾木绿草满坡，繁花似锦，芳香四溢，绚丽多彩，是一处典型的原始自然生态风光圣地。额尔齐斯河缓缓流过，岸边有桦林和成片的向日葵，牛羊满山遍野觅食撒欢，中间还夹杂着当地人洁白的毡房，一派迷人的广袤草原景色，让人仿若置身传说中霍比特人的家园。");
        info.add("巫山小三峡碧水奔流，奇峰耸立树木葱茏，猿声阵阵，河险滩绝，群鸟翻飞，构成一条美妙动人的自然山水画廊。还有秘存千古的巴人悬棺，令人费解而神往的古栈道，古风犹存、精巧质朴的大昌古镇，构成一副副令人难忘的人文景观。这一切，组成了小三峡美丽奇特的峡谷风光，成为绝妙的旅游胜地，被誉为“中华奇观”、天下绝景。");
        info.add("“谁将依天剑，削出倚天峰。”华山不仅雄伟奇险，而且山势峻峭，壁立千仞，群峰挺秀，以险峻称雄于世。自古以来，它就有“华山天下险”、“奇险天下第一山”之说。");
        info.add("敦煌莫高窟俗称千佛洞，被誉为20世纪最有价值的文化发现，是东方的卢浮宫。很多外国旅游者是这么评价莫高窟的：\"看了敦煌莫高窟，就相当于看到了全世界的古代文明。\"这绝非溢美之辞。");
        info.add("在戈壁大漠中沉睡了1500年的楼兰，留给人们太多的未解之谜：曾经繁华一时的楼兰为什么会变成这般荒凉？一个王国为何会一夜之间消失？楼兰文明究竟发展到什么程度？时至今日，这个震惊世界的历史遗迹带给人们的仍是一个个未解之谜。");


        mStackView = (CardStackView) findViewById(R.id.cardstackview);
        mActionButtonContainer = (LinearLayout) findViewById(R.id.button_container);
        mStackView.setItemExpendListener(this);
        mTestStackAdapter = new CardViewAdapter(this);
        mStackView.setAdapter(mTestStackAdapter);
        new Handler().postDelayed(
                new Runnable() {
                    @Override
                    public void run() {
                        mTestStackAdapter.updateData(Arrays.asList(TEST_DATAS),card_image,info);
                    }
                }
                , 200
        );
    }
}
