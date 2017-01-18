package org.uusoftware.burclar;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.facebook.ads.AdSettings;
import com.facebook.ads.AdSize;
import com.facebook.ads.AdView;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import java.util.Calendar;
import java.util.Date;

public class FragmentFourth extends Fragment {

    ImageView imagebutton;
    Window window;
    ActionBar actionbar;
    Intent intent;
    Context mContext;
    boolean premium;

    String fare = "Fare insanının liderlik ve öncülük özellikleri çok kuvvetlidir. Başlangıçlar yapmayı, atak davranmayı ve ilk olmayı sever. Çok çalışır, çok yoğun duygularla yaşar ve kendini yaptığı işlere tamamıyla verir. Fare insanı aşırı uçlarda dolaşmayı seven ve yoğunlaşabilen bir naturaya sahiptir. Yüzeysel de olsa farklı konularda bilgi sahibi olmak Fare insanını tatmin eder. Öğrendiklerini ya da kendine has düşüncelerini açıklamaktan hiç çekinmez. Çok yönlü ve değişken bir yapısı olan Fare insanının yaşamında enerjilerin boşuna harcanması da çok sık görülür. Ortalama ile asla mutlu olamayan Fare insanı hiç bir zaman kendini aşan işlerin altına girmekten de çekinmez. Maddi konular dışındaki riskleri bir macera gibi algılayarak, hayatın bir oyun olduğunu düşünür. Fare insanı ne kadar sıkıntılı olursa olsun, hareket etmek ister. Hareketsiz kalmak, kendine saygısını ve güvenini yitirmesine yol açarak, depreselse if bir ruh haline bürünmesine neden olur. Fare aşkta da derin duyguları olan bir insandır Sevdiği insana zaman ayırmayı, özel olarak onunla ilgilenmeyi ve sevdiğini göstermeyi tercih eden bir yapısı vardır. Sağlıklı ve tatmin edici iletişim her türlü ilişkilerinde önemli bir gereksinimdir. Sevdiği insanın hayatında her zaman ön planda olmayı ve özel bir ilgi görmeyi ister. Yalnızlıktan çok korkan Fare insanı, istenmediğini ya da sevilmediğini hissettiği zaman büyük panikler yaşar. Bu aşk acısını, cinsellikle gidermeye çalışır. Toplum içinde kontrollü olmaya, kurallara uymaya dikkat eden Fare insanı giyimiyle de dikkat çekmek ve kabul edilmek arzusundadır. Tanınmış insanlarla birarada olmak, çeşitli grup ve organizasyonlara üye olarak, toplum içinde güçlü bir konuma sahip olmayı istemek, tipik Fare özellikleri arasındadır. Paraya karşı oldukça tutkulu olan Fare insanları, nasıl para kazanılacağını çok iyi bilmelerine rağme, harcama konusunda son derece zayelse iftırlar. Yaşamlarında giderlerini minimumda tutmaya çalışarak, para biriktirirler. Fare’nin bonkörleşebilmesi için, gerçekten çok büyük bir aşk yaşıyor olması gereklidir. Fare kadını oldukça kültürlü ve bilgilidir. Dinamik bir yaşamı seven Fare kadını hem çalışmayı hem de bir aile sahibi olarak toplumda kabul edilmeyi tercih eder. Herkesle dost olabilmesine rağmen, yakın arkadaşları sınırlıdır. Sırlarını ya da problemlerini açık etmeyi sevmediği için, herkes Fare kadınının dört dörtlük bir yaşamı olduğunu düşünür. Fare erkeği dışadönük ve sosyal bir insandır. Nazik ve zarelse if davranışlarıyla, asabiyetini kontrol edebilme yeteneğiyle ve problemlere getirdiği enteresan çözümleriyle her zaman toplumsal olarak sevilir. Hareketli Fare erkeğinin el becerileri çok kuvvetlidir. Rutinden sıkıldığı için, masabaşı işlerden çok, hareket ve değişim gerektiren mesleklerde daha başarılı olur.";
    String okuz = "Öküz insanının hayatında hiç bir şey ortalama değildir. Her şeyi “iyi” ya da “kötü” olarak sınıflandırır. Olayları ve deneyimlerini geniş perspektiften ele almayı ve değerlendirmeyi tercih eder. Yaşamındaki sınırları belirlemek ve kabul etmek Öküz insanın temel gereksinimleri arasındadır. Belirsizliklerden son derece rahatsız olur. Dolayısıyla, kurallar ve kaideler hayatının belkemiğini oluşturur. Ortak yaşama girebilmesi için muhakkak karşısındaki insana güvenmesi gereklidir. Güvenini kazanan insanlara karşı oldukça açık ve verici bir yapısı vardır. Aslında katı bir görüntü sergilemesine rağmen, duygusal, duyarlı ve şefkatlidir. Değer verdiği insanlara karşı oldukça yumuşak bir tavır içindedir. Ancak kendisine karşı yapılan hatalarda esnekliğini yitirebilir ve intikam hislerine de kapılabilir. ıradesi kuvvetli, sakin ve iyi gözlem yapabilen bir insandır Öküz. ınsan ilişkilerinde sessiz kalarak, gözlemeyi ve fikir edinmeyi tercih eder. Sadık bir insan olan Öküz, geçmişe, geleneklere ve anılara son derece bağlıdır. Bir kez dost olduğunda ya da sevdiğinde, ömür boyu bırakmak istemez. Sorumluluklarına sahip bir insan olan Öküz, kuvvetli hafızasıyla iş yaşamında da oldukça beceriklidir. Enerjisini boşa harcamayı sevmediği için, her eyleminin faydalı ve verimli olmasına dikkat eder. Somutluğu tercih etmesine karşı, soyut kavramlardan da uzak değildir. Ama yine de hayaller ya da fantaziler yaşamının ana parçaları değildir. Hareketsizliğe çok eğilimli olan Öküz insanı, sanki gösterdiği direnç ve atalet ile gücüne güç katar. Uzun yıllar aynı mekanda yaşamak ya da aynı işte çalışmak, Öküz’ü hiç rahatsız etmez. Bu hareketsizlik, kimi zaman inat olarak da tezahür eder. Sabitliğe olan yatkınlığı ve değişime karşı gösterdiği direnç, diğer insanlar tarafından kimi zaman sıkıcı ve boğucu bir insan olarak nitelendirilmesine neden olabilir. Öküz insanı yemek yemeği çok sevdiği için genellikle kilo almaya eğilimlidir. Ancak çelik gibi iradesiyle, kendini kontrol altına almaya başladığı zaman, istediği mükemmel görünüme de kavuşabilir. Öküz’ün kendi istediği değişiklikleri kusursuz bir şekilde gerçekleştirebilme kapasitesi vardır. Öküz kadını, tuttuğunu koparan ve cesaretli bir karakterdedir. Ailesine çok bağlı olan bu kadın genellikle tekeşli olmayı ve sadık kalmayı tercih eder. Kendisi de aldatılmaya dayanamaz. Doğayla tam bir uyum içinde yaşayabilen Öküz kadını, çok düzenlidir. Sorumluluklarını gayet iyi bilir ve bunları tam zamanında yerine getirebilmek için, kendine mükemmel bir düzen yaratır. Zaman zaman kendine dönme ve içine kapanma alışkanlığı da vardır. Öküz erkeği, yaşamında taviz vermekten hoşlanmayan, kendi istediklerinden zor vazgeçen bir yapıya sahiptir. Ancak sevdikleri için esneklik gösterebilir. Yoksa yaşamı boyunca kendi ilkelerinden vazgeçmesi olanaksızdır. Öküz erkeğinin iş yaşamı hayatının en önemli alanıdır. Evde, uykuda, eğlencede, kısacası her yerde aklı işindedir. Hayatını güvence altına almak, gelecekle ilgili kaygısız bir yaşam sürebilmek için durmaksızın çalışır.";
    String kaplan = "Kaplan insanı dünyadaki yaşamı yönlendirebilecek kadar güçlüdür. Geniş ölçekli işlerle uğraşmaktan, kitleleri ayağa kaldırmaktan ve idare etmekten hoşlanır. ınsanların ilgisini kendi üstüne çekmeye bayılır. Kaplan insanının reaksiyonlarını, amaçlarını ve hedeflerini önceden bilebilmek neredeyse imkansızdır. Hayatı bir oyun gibi gördüğü için, yaşamında risk almaktan, değişik yöntemler kullanmaktan ve farklı yollardan ilerlemekten hiç çekinmez. Korkusuzluğu nedeniyle, hiç bir kuralı ya da geleneği kabul etmeyen, yaşamını kendi kaideleriyle devam ettiren bir yapısı vardır. Birlikte olduğu insanlara ya da en sevdiklerine bile, gerekçe belirtmekten nefret eder. Açıklama yapmak gereği, Kaplan insanının uzaklaşması için yeterli bir nedendir. Yapıcı eleştiriler bile, bir Kaplan’ın tahammül sınırlarını zorlar. Doğuştan getirdiği asaleti, insanlara karşı bonkör, verici ve koruyucu bir rol üstlenmesinde etkendir. Başkaları için bile mücadele etmekten çekinmez ve hakkını elde edinceye kadar yılmaz. Aslında iyi niyetli bir insan olan Kaplan, hayatın kendisine her zaman gülümseyeceğini düşünerek, risk almaktan da hiç çekinmez. Kaplanlar büyük işlerin peşinde olup, büyük riskler aldıkları için de, yaşadıkları yıkımlar ve problemler de büyük ölçekli olur. Zor yollardan öğrenmek ve kişiliğini geliştirmek tipik bir Kaplan özelliğidir. Sıradan olan her şey Kaplan’ın çabuk sıkılmasına nedendir. Kendindeki asaleti yaşamına yansıtmak istediği için, hiç bir şeyle kolay kolay tatmin olmayı beceremez. Aşk ilişkilerindeki değişiklik ve mükemmelik arayışı yüzünden aşk defteri oldukça kalındır ya da pek çok defa evlenebilir. Yalnız başına hareket etmeyi ve karar almayı seven Kaplan’ın birisi tarafından sahiplenilmeye ve kısıtlanmaya, başka bir deyişle “terbiye edilmeye” asla tahammülü yoktur. Dostluklarında ve birlikteliklerinde bağımsızlığını koruyabileceği ilişkileri ve en az kendisi kadar hareketi ve macerayı seven insanlarla birlikte olmayı ister. Özgün olma isteği, farklı ilişkiler yaşamasına nedendir. Kaplan kadını özgürlüğünden vazgeçemediği için ya çok geç yaşlarda evlenir ya da bekar kalmayı tercih eder. Enteresan bir kadın olan Kaplan, geleneklere ve kurallara karşı bağımsız olduğu için, norm dışı beraberlikler oluşturmaktan da hiç çekinmez. Dişil çekiciliğinin son derece farkında olan Kaplan kadını, iş yaşamında bu özelliğinden de faydalanmayı normal karşılar. ış hayatına diğer kadınlara oranla daha fazla önem verdiği için, çalışarak önemli pozisyonlara ulaşmayı amaç haline getirir. Kaplan erkeği de kadını gibi bağımsızlığından vazgeçemediği için, sorumluluk yaratan ilişkilerden uzak kalmayı tercih eder. Kolay elde edilen kadınlardan hoşlanmaz ve ilişkilerini çok çabuk bırakır. Birlikte olduğu kadının, kendisini her yönüyle beğenmesini ve bu takdirini de açıkça belirtmesini bekler. Kendisinin en güzel kadına, en iyi eve, en lezzetli yiyeceklere ve en ihtişamlı yaşama layık olduğunu düşünerek, kolayca tatmin edilemeyen hedeflerin peşinden gider.";
    String tavsan = "Tavşan insanı sessiz, sakin ve kendi gizemlerini saklamak isteyen bir yapıdadır. Çok dışarı dönük olmayan bu insan, kendi dünyasında kalmayı ve güvenliğini korumayı tercih eder. Yeniliklere açık gibi görünmesine rağmen, aslında eskiye bağlılığını korumayı bilir. Evine son derece düşkğn ve bağlı olduğu için, hayatını daha çok burada geçirmek isteyerek, her türlü konforu oluşturmaya gayret eder. Düzen ve sistem Tavşan için yaşamın temel gereksinimlerinin başında gelir. şekilci bir insan olan Tavşan, görsel çirkinliklere ve düzensizliklere karşı da tahammülsüzdür. Uyumsuzluğa dayanamayan Tavşan insanı, hayatında problem olarak gördüğü her ilişkiyi, tamamıyla bitirmek ve yok etmek eğilimindedir. Trajediye yatkın bir yapısı olduğu için, her türlü küçük anlaşmazlık bile Tavşan tarafından en büyük dünya savaşlarından daha acı verici olarak nitelendirilir. Sevmeye ve sevilmeye karşı ihtiyaçları hiç tükenmez. Kabul edilmek ve sevildiğini tekrar tekrar duymak, Tavşan insanının önemli duygusal gereksimleri arasındadır. ılişkilerdeki saldırganlık, acele ve baskı tavşanın uzaklaşmasındaki en önemli nedenler arasındadır. Entellektüel olarak gelişkin bir kapasiteye sahip olan Tavşan insanı, aklını kullanabileceği işlerde oldukça başarılı olabilir. Zaman zaman entellektüel yanlarının baskın şekilde ortaya çıkması, ukala olarak nitelendirilmelerine neden olabilir. Ancak huzursuzluklara karşı dayanıksız yapısı, meslek hayatındaki anlaşmazlıklardan ve rekabetten uzak kalmak istemesine, dolayısıyla çok yüksek pozisyonlara ulaşamamasına nedendir. Toplumsal ilişkilerinde belli bir seviyeyi tutturmak isteyerek, nazik ve cana yakın bir davranış biçimi geliştirir. Tavşan burcu insanlar, acılı olaylara karşı son derece hassastırlar. Trajik olaylara hem son derece üzülürler hem de bu olayları takip etmekten kendilerini alamazlar. Açlık ya da terör gibi geniş ölçekli olaylar karşısında gerçekten çok üzülür ve kendilerine ders çıkarırlar. Ancak bir Tavşan’ın kendi düzenini ve emniyetini tehlikeye atabileceği, bir olaya karışması zaten mümkün değildir. Tavşan kadını sessizliğinin altında oldukça çekici ve karşı cinsi nasıl etkileyebileceğini bilen bir insandır. Kendi kişisel görüntüsüne ve bakımına çok önem verir. Evinin de temiz ve düzenli olması için elinden geleni yapar. Risk almaktan hoşlanmayan Tavşan kadını, bağımlılığı sever. Kendisi sevmese bile, sevildiğinden emin olduğu birliktelikleri uzun süre devam ettirme, hatta hiç kopratmama eğilimindedir. Tavşan erkeği etkileyici sesi ve konuşma tarzı ile, karşı cinsi kendine çekmeyi bilir. Kişisel çalışmalardan çok ekip çalışmalarında başarı elde eder. Ama kalabalıklardan pek hoşlanmıyor olması problemler yaşamasına nedendir. Sorunlu durumları hayatında istemeyen Tavşan erkeği, radikal çözümler üretmekte ustadır. Riske girerken çok dikkatli davrandığı gibi, olası negatif durumları da önceden hissederek, gerekli önlemleri alır.";
    String ejderha = "Ejderha insanları kendilerinden son derece emin, güvenli ve girdikleri her ortamda dikkat çeken tiplerdir. Hükmetmeyi seven yapısı, yöneticilik ve idarecilik türü işlerden büyük zevk almasına nedendir. Doğal bir çekiciliğe sahip olan Ejderha burcu insanları etrafındakileri de istekleri gibi yönlendirmekten de hiç çekinmezler. Tereddütsüz yaklaşımları ve vakit geçirmeden düşüncelerini eyleme döken yapısıyla, Ejderha diğer insanların üzerinde de harekete geçirici bir etkiye sahiptir. Kendi amaçlarının peşinden gitmek için pek çok şeyi feda edebilecek kadar gözü karadır. Kendine karşı son derece toleranslı olan Ejderha insanı, kendi hatalarını küçümseyen, önemsemeyen ve başka insanlardan da aynı esnekliği bekleyen bir yapıdadır. Otokritik mekanizmasını hiç çalıştırmayan Ejderha insanı, kendi mükemmeliyetine doğuştan inanmıştır. Ancak kendisi yanlışlara ve hatalara karşı aynı esnekliği göstermekte zorlanır. Affetmesi ve unutması oldukça zordur. Lükse ve para harcamaya karşı inanılmaz bir eğilimi vardır. Kendisinin en iyi şeylere layık olduğunu düşündüğü için, pahalı giyimlerden ve pahalı mekanlardan uzak durması imkansızdır. Gösterişi ve farkedilmeyi de seven Ejderha, yalnızca hakkında konuşulmasını istediği için bile para harcayabilir. Kendine çok dönük olmasına rağmen, Ejderha insanı aslında yufka yüreklidir. Özellikle sevdiklerine, dostlarına ve aile üyelerine karşı dayanıksız ve hassas bir insandır. ıçgüdüleri de çok kuvvetli olan Ejderha insanı, diğer insanların ihtiyaçlarına cevap vererek, kendi büyüklüğünün ve gücünün hazzını yaşar. Oldukça analitik bir zekaya sahip olan Ejderha insanının hafızası da çok kuvvetlidir. Analiz sürecinden sonra vardığı sentez genellikle yanılmadan, doğruya götürür. Yönetici olabileceği, karar alıp, inisiyatif kullanabileceği her türlü işte oldukça başarılı olur. Politika ve liderlik gerektiren konumlar kişisel tatmin ve başarı elde edebileceği alanlardır. Ejderha kadını oldukça atak, kendi dişil cazibesinin ve etkileyiciliğin farkında olan bir insandır. Egosunu tatmin edebilmek amacıyla, özellikle erken yaşlarında çok çapkınlık yapmaya ve sevgili değiştirmeye eğilimlidir. Ejderha kadını için güç ve gövde gösterisi önem taşıdığından, bu ihtiyaçlarını tatmin edebilecek bir erkekle hayatını birleştirmeyi tercih eder. Hayat standardını yüksek tutmak, lüksten vazgeçememek, kendini her şeye değer bulmak tipik Ejderha kadını özellikleri arasındadır. Ejderha erkeği, hayatına yapılabilecek müdahalelerden hiç hoşlanmaz. Dolayısıyla kendisini olduğu gibi kabul edecek, zaaflarına engel olamayacak, kendinden daha pasif ama fiziğiyle dikkat çekici kadınları tercih eder. Gücün her şey olduğuna inandığı için, meslek yaşamında da hedeflediği noktaya ulaşıncaya kadar elinden geleni yapar. Bol para ve lüks yaşama da düşkün olan Ejderha erkeğinin takdire oldukça ihtiyacı vardır. Önde olmak, farkedilmek için mümkün olan her fırsatı değerlendirir.";
    String yilan = "Yılan insanları mağrur, sessiz ve etkileyicidirler. Hayatlarında hiç bir hedefe direkt olarak gitmeyip, dolambaçlı yolları tercih eden bir yapıları vardır. Elde edilmesi ve ilişki kurulması oldukça zor bi karaktere sahiptir Yılan. Sessizliğine ve sakinliğine rağmen Yılan fark edilmeyi ve takdir görmeyi ister. Fiziksel dış görünüşünün önemli olduğunun bilincinde olan Yılan insanı sadece bununla yetinmek istemez. Davranış biçimiyle, aklıyla ve duygularıyla da diğer insanları etkilemek, Yılan’ın en büyük amaçları arasındadır. Yılan hayatta iyi şeylerden hoşlanır ve kendi etkileyiciliğini arttırmak için para harcamaktan hiç kaçınmaz. Etkileyiciliğini arttırmak için beyaz yalanlar söylemekten de çekinmez. Problemli durumlarla uğraşmaktan, çözüm üretmekten nefret ettiği için, Yılan kaçmayı ve uzaklaşmayı tercih ederler. Dolayısıyla yaşamında yarım kalmış pek çok ilişki ve iş vardır. Yılan insanlarının finansal konularda doğuştan getirdikleri bir yetenekleri vardır. Kendilerine göre temel ihtiyaçlarını karşıladıktan sonra bile, tatmin olamayarak, servet edinme ve zengin olma yarışına devam ederler. Toplumsal olarak dışlanmak ya da ikili ilişkiler içinde reddedilmek, istenmemek Yılan’ın önce duygusal ve sonra da fiziksel olarak sağlığının bozulmasına neden olabilir. Özgüvenini ve sağlığını koruyabilmesi için kabul gördüğünden muhakkak emin olması gereklidir. Aslında uzalşmacı bir yapısı yoktur ama, insanları da karşısına almaktan hoşlanmaz. Stratejik ilişkilerde oldukça yeteneklidir ve doğal hareket eder. Bu özelliği mesleki yaşamında da başarılarının garantisidir. Yaşam standardını korumayı hedefleyen Yılan insanı, iş hayatında tedbirli davranmaya çalışır. Geleneklere ve rutine karşı eğilimi olan Yılan, toplumsal normların dışına da çıkmak istemez. Dolayısıyla zaman zaman özgünlüğünü, orijinalitesini ve yaratıcılığını kaybetme tehlikesiyle karşı karşıya kalır. Yılan kadını güvene ihtiyaç hisseder. Kendisini geliştirmektense, gelişmiş bir erkeği almak tam Yılan kadınına göre bir hedeftir. Aslında tutkulu bir kadın olmasına karşın, toplumsal kurallara verdiği önem, kendini kontrol etmesindeki en önemli etkendir. Hırslı olmasına rağmen, çok çalışkan ve üretken olmayan Yılan kadını, kendine vakit ayırmayı sever. Zekasını tam kapasite kullanmayan Yılan kadını, yine de etrafındaki insanlarda entellektüel ve akıllı bir insan imajı yaratır. Yılan erkeği oldukça nazik ve stratejiktir. Herkese nasıl davranması gerektiğini içgüdüsel olarak saptayabildiği için, istediği kadını elde etmekte ustadır. Aşka çok düşkün olan Yılan erkeğinin, aslında gözlerden saklamak istediği yoğun bir duygusallığı da vardır. Girişimci bir yanı olmasına rağmen, korkuları fazla ilerleyememesindeki en büyük etkendir. Kolay güvenemeyen Yılan erkeği, finansal konularda tedbirli ve sağlam adımlar atarak, zenginleşmek amacındadır. Meraklı yapısı pek çok farklı konuda bilgi edinmesine ve edindiklerini meslek yaşantısında kullanmasına nedendir.";
    String at = "At insanları zorlamalardan, baskıdan ve zorunluluklardan nefret ederler. Çin Zodyak’ındaki özgürlüğüne en düşkün burç At’tır. Yetenekli ve kendine yeten bir insan olan At, hayatını sonuna kadar özgür yaşamak için, elinden geleni yapar. At insanı bağımsızlığını elde edebilmek için, erken yaşlardan itibaren çalışmaya başlar. Kendisi başka insanların hayatına müdahale etmediği gibi, kendisine de karışılmasından hiç hoşlanmaz. Doğuştan soylu bir davranış şekli olan At’ın aslında reaksiyonlarını ve cevaplarını önceden tahmin edebilmek pek mümkün değildir. Gelişkin bir hitabet yeteneği olan At’ın, ikna kabiliyeti ve inandırıcılığı da kuvvetlidir. Kendi doğrularıyla hareket eden At, genellikle belirsizlikler karşısında hırçınlaşır. Varolan şartları değiştirmek ve kendine uygun bir düzene sokmak, tipik At karakteristikleri arasındadır. Genellikle çok zeki olan At insanları, kendi amaçlarına hizmet eden her hangi bir işte, uzun süre, hiç sıkılmadan, sabırla çalışabilirler. Ancak emeğinin karşılığını alamayan bir At, hakikaten çileden çıkarak, kavgacı ve hırçın bir ruh haline girebilir. ısyankar bir yapısı olmasına rağmen, toplum içinde belli bir yer edinmek ihtiyacındadır. Birlikteliklerinde de bu ihtiyacını tatmin edebileceği bir eş arar. Ancak bağımsızlığına ve bireyselliğine verdiği önem ilişkilerindeki problemlerin en büyük kaynağıdır. Önyargıları kuvvetli olmasına rağmen At insanı, pragmatik bir şekilde ikna edilebilir. Hatalara karşı tolerans gösterebilir ama kendi hatalarını kabul ederek, bakış açısını değiştirmekte direnç gösterir. Gösterişe ve lükse karşı tutkusu olmayan At insanı, yine de farklı olmayı ve dikkat çekmeyi doğal davranışlarıyla rahatlıkla becerir. Sosyal ortamlarda rahat hareket edebilmesine rağmen, ön planda olmayı ve sahnede bulunmayı sevmez. Kimi zaman soğuk ve mesafeli bir insan olarak da algılanır. At kadını kendi özelliklerinin farkında olan, üretken ve çalışkan bir insandır. Duygusal yanı oldukça kuvvetli olmasına rağmen, mantık da hayatında önemli bir yere sahiptir. Tüm aşk acılarından bile aklını kullanarak kurtulmayı becerir. Bağlılıktan ürkmesine rağmen, kalıcı ilişkileri de sever. Yine de her ilişkisinde kendi kişisel güvenliğini elinde tutabilmek için, çalışmaya ve üretmeye devam eder. At kadını kendi duygularını, ihtiraslarını ve dürtülerini o kadar sıkı kontrol eder ki, soğuk ve hatta duygusuz olarak bile tanınabilir. At erkeği eğitime verdiği önem yüzünden, akademik kariyer ya da medya gibi alanlarda ciddi başarılar elde eder. Ancak kendi kurallarıyla hareket etmek istemesi, isyana eğilimli yapısı, hiyerarşik mekanizma ,içinde uyum sağlayamamasının, problemler yaşamasının en temel gerekçesidir. Bağımsızlığına düşkün olan At erkeğinin aslında duygusal yanı kuvvetlidir. Fakat çok eşliliğe yatkın olduğu için, sadakat gösteremez. Duygusal yanı düşünsel yanına yenilen At erkeği, aşık olduğunda bile kontrolünü kaybetmez.";
    String keci = "Keçi insanları kendi hayal dünyalarında yaşayan, yaşamlarında hiç bir zaman düzen ve organizasyon yaratmaya ihtiyaç duymayan kişilerdir. Oldukça yumuşakbaşlı ve diğer insanların yönlendirmesine açık bir yapıları vardır. Etraflarındaki insanları mutlu etmek, hoşnut kılmak için ellerinden geleni yaparlar. Uyumlu yapıları gereği, insanlarla ilişkileri her zaman son derece mükemmeldir. Hayatta savaşmaktan, huzursuzluktan ve mücadeleden nefret ettikleri için, güvenecekleri ve sırtlarını yaslayayacakları bir insana ihtiyaç hissederler. Keçi’nin iradesi zayıf değildir ama, başka bir insana zorla bir şey yaptırtmak, kabul ettirmek, kendi yaralanmasına ve acı çekmesine yol açar. Sezgileri son derece kuvvetli olan Keçi insanı, çok zengin bir hayal dünyasına sahiptir. Bu zengin hayal dünyasının esinlerini kullanabileceği moda ya da sanat gibi konularda çok başarılı olur ve tatmin elde edebilir. El yetenekleri de oldukça gelişkin olan Keçi, bu tür mesleklerde de kendinden bir şeyler katarak, büyük başarılar elde edebilir. Aslında plansız programsız olmasına rağmen, dayanıklı bir insandır Keçi. ınandığı ve gönül verdiği projeler için çelik gibi bir irade ile sonuna kadar sabır gösterebilir. Ama yine de sistem yaratamamak gibi bir özelliği olan Keçi, ağır sorumluluklar altına girmek, zor kararları uygulamak gibi ciddi gereksinimleri olan mesleklerde başarılı olabilecek bir yapıda değildir. Spontanlık ve duygulara göre hareket etmek tam Keçi tarzıdır. Ruh halindeki dalgalanmalar da ilişkilerinde belirleyiciliğe sahiptir. Keçi’yi iyi tanımayan insanlar, O’na alışıncaya kadar büyük şaşkınlıklar yaşayabilir. Keçi duygusal yapısıyla, sadık ve vefakar bir insandır. Özellikle güvene ve yönetilmeye ihtiyaç duyduğu için, tek eşliliğe daha yatkındır. Ancak duygusal ihtiyaçları da çok ön planda olan Keçi insanı, beraberliğinin ilerleyen zamanlarında bile, yeni bir aşk aramak için yıllanmış eşini terk edebilir. Keçi’nin eşi kadar ailesi ve çocukları da hayatının merkezinde bulunur. Çocukları için her türlü fedakarlığı yapabilen Keçiler, oldukça iyi ebeveyn olmalarına rağmen, otorite kurmakta zorlanırlar. Keçi kadını sezgileriyle hayatını yönlendiren, kendini duygularına bırakan bir yapıdadır. Sevdikleri için hiç bir fedakarlığı yapmaktan çekinmez. Aşka aşık olan Keçi kadını, duygusal anlamda tatmin olmazsa ya da şüphe yaşarsa, tırnaklarını çıkarabilir ve radikal kararlar alabilecek hale gelir. Duygusallık her şeyin önüne geçtiği için, cinselliği de duygusallık olmadan yaşayamaz. Resmi birlikteliklerinde bile, masumluğunu ve utangaçlığını üzerinden atması oldukça uzun süre alabilir. Keçi erkeği de kadını kadar hassas, gelenksel hayat mücadelesine uyum sağlamakta zorluk çeken bir yapıya sahiptir. Para harcamaya olan merakını, para kazanmak için gösterse, milyarder olabilir. Kadınların kendisine söz geçirmesine ya da yönetmesine karşı eğilimi vardır. Dominant kadınlardan hoşlanan Keçi erkeği, kendinden daha güçlü ve becerikli bir kadın bulduğu zaman tüm sorumluluklarını onun üzerine yıkabilir.";
    String maymun = "Çin Zodyakı’nın en neşeli insanı Maymun’dur. Özgürlüğüne At kadar düşkün olan Maymun, baskılardan ve yönlendirilmekten nefret eder. Adaptasyon kabiliyeti çok gelişkin olan Maymun burcu insanları, zengin bir deneyim rezervine sahiptirler. Analitik bir zekaya sahip olan Maymun insanı, problemlere kimsenin bulamadığı çözümleri üretmekte tam bir ustadır. Oldukça farklı çalışan zekasıyla, etrafındaki insanları şaşırtmaktan ve kendi zekasına bir kez daha hayran kalmaktan büyük keyif alır. Bildiklerini öğretmek, insanları eğitmek gibi bir misyonu olduğunu düşünür Maymun insanı. Bu durum pek çok zaman eğlenceli olmasına rağmen, kimi zaman da diğer insanlara uygulanabilecek bir baskı şeklini alır. Meslek hayatında da parlak zekası ve enteresan çözümleri ile fark edilir. Rakamlarla, tablolarla ve formüllerle arası çok iyi olan Maymun, finans, muhasebe ya da mühendislik gibi alanlarda büyük başarılar elde edebilir. Maymun kimi zaman çocuksu bir davranış biçimi içine de girer. Ne zaman ciddi olduğunu, ne zaman şaka yaptığını anlamak güçleşebilir. Ama yine de dostlarını eğlendirmek ve mutlu etmek için kişisel becerilerini kullanmaktan hiç kaçınmaz. Geleneklere ve kurallara karşı içten gelen bir karşıtlığı olduğu için, toplumda asi olarak nitelendirilmese bile, marjinal olarak tanınır. Bu farklı karakteri girdiği her ortamda sivrilmesine ve dikkat çekmesine neden olur. Adil ve eşitliği seven, demokrat bir tarzı vardır. ılişkilerinde maddi alış-verişlerden uzak kalmaya çalışır. Bazen de kendi istekleri doğrultusunda insanları yönlendirmekte ya da beyaz yalanları kullanmakta hiç tereddüt etmez. Bağımsızlığına oldukça düşkün olan Maymun insanı, aşk ilişkilerinde de sadakatsiz bir yapı sergiler. Değişiklik ihtiyacı ve bağlanma korkusu, uzun yıllar pek çok ilişki yaşamasının hatta hiç evlenmemeyi tercih etmesinin temel gerekçesidir. Maymun kadını içsel çelişkiler yaşayan bir insandır. Kendi çocuksuluğuyla, erişkin hatta “hocanım” tarzı arasında kimi zaman kendini sıkışmış hisseder. Neşeli ve sevecen bir kadın olan Maymun, eşinin ya da birlikte olduğu insanın kendisiyle beraberken eğlenmesi için elinden geleni yapar. Ev yaşamında olduğu gibi, iş hayatında da oldukça aktif bir yapısı vardır. Ancak oldukça meraklı bir yapıya sahip olan Maymun kadını, sadece diğer erkeklerle yaşanabilecek ilişkileri merak ettiği için, eşini aladatabilir. Bu merak arkadaşları ve dostları arasında da dedikoduculuğa yol açabilir. Maymun erkeği de kadını kadar eğlencelidir. Birlikte olduğu kadınları espri yeteneğiyle etkiler, güldürerek kendine bağlar. Duygularını geri planda tuttuğu için yüzeysel gibi görünse de, aslında derin bir sevilme ihtiyacı vardır. Maymun erkeği teknik konularda oldukça başarılıdır. Esnek ve adaptasyon yeteneği kuvvetli olan Maymun erkeği, iş yaşamında her hangi bir pozisyonda oldukça başarılı olabilir. Çok büyük hedefleri ve amaçları olmayan Maymun erkeği, kendisi sıkılmadığı sürece uzun müddet aynı işi de yapabilir.";
    String horoz = "Horoz burcu insanlar, somut konularla ilgilenmeyi severler ve bu tür işlerde başarılı olurlar. Açık yürekli ve hayatının hiç bir alanını saklamaya gerek duymayan bir yapısı vardır. Yalansız ve samimi ilişkiler kurmak ister. Skalasında gri rengi olmayan Horoz insanı, aşırı uçlarda gidip gelme eğilimindedir. Katı kararlar almaya ve yargılara varmaya yatkındır. Son derece dayanıklı bir yapısı vardır. Yaşadığı her şeyi olduğu gibi kabul eder, en ağır deneyimlerin altından bile kalkar, yıkımlardan sonra canlanır. Oldukça soğukkkanlı bir insan olan Horoz, yaşamı ve gelişen, değişen şartları kontrol edebileceği inancındadır. Kendine oldukça güvendiği için, yardım almaktan ve desteğe ihtiyaç duymaktan nefret eder. Çok keskin bir zekaya sahip olan Horoz insanları, önemli konuları dikkatli ve eleştirel bir gözle ele alarak değerlendirirler. Bu alışkanlıkları öngörü yeteneğinin doğmasına neden olduğu için, uzun vadeli planlarda Horoz oldukça başarılıdır. Hayata ve insanlara karşı gerçekçi bir yaklaşım geliştiren Horoz insanı, tüm ilişkilerinde kendini açıkça ortaya koyduğu gibi, diğerlerinin de açık ve samimi davranmasını bekler. Her türlü acıya ya da kırgınlığa, gerçeğe ulaşabilmek için katlanabilme kapasitesindedir. Hayallerden, ütopyalardan son derece uzak olduğu için, iş yaşamında da oldukça iyi pozisyonalara gelmeye adaydır. Somutluğa olan merakı yüzünden, adımlarını sağlam atmayı bilir. Aşk ilişkilerinde de açık, dürüst ve sadık kalmayı tercih eden bir yapısı vardır. Fiziksel çekiciliğe önem veren Horoz insanı, kendisi kadar dış görünümüne önem veren bir insanla beraber olmayı ister. Toplum içerisinde ciddi tavırları ve ölçülü davranış biçimi ile kabul gören bir insandır. Ancak duygularını pek göstermekten hoşlanması, insanların kendisinden şüphe etmesine neden olabilir. Aslında arkadaşlarına ve dostlarına karşı son derece sevecen olan Horoz, elindeki tüm kaynaklarını onlar için kullanmakta hiç tereddüt etmez. Evine gelen misafirlerini ağırlamak için büyük çaba harcaması da bu özelliğinin bir uzantısıdır. Horoz kadını iş hayatına oldukça kolay adaptasyon sağlayan ve başarılı olan bir insandır. Aynı başarıyı aşk ilişkilerinde de dürüstlüğü ve sadakati sayesinde elde eder. Ancak inatçılığa olan yatkınlığı, kimi zaman eşiyle ya da partneriyle yaşadığı problemlerin temel kaynağıdır. Sevdiği insana karşı baskı uygulamaktan ve onu kısıtlamaktan da kendisini alamaz. Rutin bir hayatı tercih eder ve kurduğu düzenin dışına çıkmak ihtiyacı hiç hissetmez. Horoz erkeği ciddi, tutarlı ve attığı her adımı hesaplayan bir insandır. Kimi zaman ilişkilerinde dominant bir karakter sergilemesi de olasıdır. Ailesine ve sevdiklerine karşı verici ve koruyucu bir rol üstlenmekten çekinmez. Güvenilirliği ve açıklığı, tercih edilen bir erkek olmasında etkendir. Horoz erkeğinin iş hayatı oldukça önemlidir. Kendine ve kapasitesine çok güvendiği için, iş arkadaşları tarafından ukala olarak nitelendirildiği çok görülür. ış yaşamındaki problemlerin hayatının her alanını etkilemesine izin vermesi, en önemli negatif yanlarından birisidir.";
    String kopek = "Köpek insanları Çin Zodyakı’nın en ciddi, en olgun, hatta yaşlı ruhlu denilebilecek insanlarıdır. ıçinde yaşadığı düzenin olumsuzluklarını çok net gören ve sürekli eleştiren Köpek insanı kolay kolay mutlu olamaz. Çevresel şartları da, sosyal şartları değiştirmek için çocukluğundan orta yaşına kadar uğraşır Köpek insanı. ınsanların iyiliği için onların temelde iyi olduklarını düşünerek, tüm ömrü boyunca mücadele etmekten kaçınmaz. Ancak en büyük hayal kırıklıklarını da bu alanda yaşar. ınsanları çok sevmesine rağmen, çok yoğun bir sosyal yaşamı tercih etmeyen Köpek insanları, dış görünümlerine ve fiziksel çekiciliğe de hiç önem vermezler. Ciddi ve düşünen bir insan olan Köpek, girdiği sosyal ortamlarda da fazla eğlenemez ve rahatlayamaz. Eğlenen insanlara karşı da anlamsız ve boş gözlerle bakmaktan kendini alamaz. Köpek insanı hangi mesleği yaparsa yapsın, mukakkak geri planda kalmayı tercih eden bir yapıdadır. Toplumsal konularla yakından alakalı olduğu için, sosyal meslekler Köpek insanının hem başarılı olacağı, hem de tatmin elde edeceği alanlardır. Onurlu ve gururlu bir insandır Köpek. Duygularını belli etmekten utanır. Genellikle insan ilişkilerinde mesafeli kalmayı tercih eder. Ama sürekli dikkatlidir ve savunma mekanizmalarını hazır tutar. Kalkanlarını kaldırdığı zaman kendisine ulaşmak neredeyse imkansız hale gelebilir. Her zaman yaşından çok daha olgun bir ruha ve davranış biçimine sahip olan Köpek insanı idealleri yüzünden kendini çok kolayca aşka, sevgiye ve ilişkilere kaptıramaz. Özgünlüğünü anlayabilecek, kendisine idealist anlamda katılacak birisiyle beraber olmayı tercih eder. ılişkilerinde ciddi, demokrat ve eşitlikçi bir tutumu vardır. Birlikte olduğu insanın haklarına saygı gösterir, isteklerine karşı duyarlı davranır. Ama bunun dışında “kör aşık” hiç bir zaman olmaz. Sevgilisini hediyelere boğması, lüks yerlere götürmesi, aşırı para harcaması söz konusu bile değildir. Köpek insanı affetmekte zorlanan bir yapıya sahiptir. Yapılan hatalar karşısında toleranslı gibi gözükerek, affetmiş gibi yapsa da, aslında içsel seviyede kırılmış olanın yerine konması Köpek için imkansızdır. Köpek kadınları iş yaşamlarını her şeyi önünde tutarak, özel yaşamlarını ve duygusal hayatlarını ihmal etme eğilimindedirler. Aslında son derece yumuşak bir insan olmasına rağmen, keskin diliyle anlaşmazlıkların tohumunu atmakta üstüne yoktur. Yine de sorumluluklarının çok farkında olduğu için, yaşamda üzerine düşen duygusal vazifelerini de yerine getirmekten geri kalmaz. Eşine ve çocuklarına karşı oldukça disiplinli bir davranış şekli vardır. Köpek erkekleri kolay kolay tatmin olamayan, şüpheci yapıda insanlardır. Endişeli bir insan olan Köpek erkeği, bu yapısını karşısındaki insana yansıtmakta da tereddüt etmez. ış yaşamında geri planda kalmayı tercih ettiği için, büyük ödüller peşinde de değildir. Dürüst ve ciddi bir yapısı olan Köpek erkeği iş arkadaşlarıyla da anlaşmazlıklar yaşamaya eğilimlidir. ış yaşamının kaypaklıkları ve kolayca yıkılan kuralları karşısında tüm düzenden ve insanlardan uzaklaşmayı tercih edebilir.";
    String domuz = "Domuz insanı Çin Zodyakı’nda güvenilirliğiyle tanınır. Son derece duyarlı, duygusal, insanlara ve insan ilişkilerine önem veren bir yapısı vardır. Evliliğe, bağlanmaya ve tek eşliliğe çok yatkındır Domuz insanı. Domuz insanı başkalarının isteklerine göre kendi isteklerini ve programlarını değiştirmeye yatkındır. Özellikle sevdiği insanların ricalarını bile bir emir gibi algılayarak, hiç sorgulamaksızın yerine getirmeye gayret eder. Bu özelliği pek çok zaman kendisi için yaşayamamasına, kendi gereksinimlerini göz ardı etmesine ve gelişimini tamamlamakta gecikmesine nedendir. Aynı özellik pek çok kere manipülatif bir yapının ortaya çıkmasına ve suistimaller yaşamasına da neden olur. Mesleki alanda oldukça başarılı bir insandır Domuz. Son derece duygusal ve verici olmasına rağmen, iş hayatına geldiğinde gözünü açar. Gerekenleri yaparak, kendisini amaçlarına ulaşmakta engelleyebilecek tüm unsurları ortadan kaldırmak için mücadele eder. Kültür ve sanata yatkınlığı, özellikle bu konularla bağlantılı mesleklerde başarılı olmasının temel nedenidir. Yine de huzura ve sukünete duyduğu ihtiyaç, yoğun mücadelelerin ve rekabetin bünyesini ve sinir sistemini zayıflatmasına neden olur. Hayatı ve kendisi için yüksek standartlar koyan Domuz insanı, iş yaşamında da aynı beklentiler içindedir. Dolayısıyla her hangi bir pozisyonda olmak kendisini rahatsız eder. Statüye verdiği önem, farkedilen bir pozisyonda olmak istemesinin temel gerekçesidir. Aşka ve sevgiye son derece düşkün olan Domuz insanı, geleneklerin dışına çıkmak istemez. Birlikteliklerinde normlar dahilinde hareket etmeyi ve standardları aşmamayı hedef haline getirir. Bu özelliği Domuz’un erken yaşlarda evlenmesine nedendir. Karşısındaki insanı olduğu gibi kabul Eden Domuz insanı, aslında fiziksel çekiciliğe ve ruh güzelliğine karşı da duyarlıdır. Eşinin farkedilen birisi olmasından gurur duyar ama, kıskanmaktan da kendisini alıkoyamaz. Kıskançlığının sonucunda karşısındaki insanı kısıtlamak ya da denetlemek istediği zamanlar da olabilir. Domuz kadını, kalıcılığa önem veren, inasanları seven, onlar için bir şeyler yapmaktan keyif olan bir insandır. Sevdiklerine en güzel şeyleri yaşatmak için elindeki tüm kaynaklarını kullanabilir. Domuz kadını birlikte olduğu erkeğin, elit bir tabakadan ve güçlü olmasını ister. ıyi döşenmiş ve güvenliği sağlanmış bir ev, güzel giyecekler ve misafirlerine sunabileceği nadide yiyecekler Domuz kadınının mutluluğunda önemli role sahiptir. Domuz kadını birlikte olduğu erkeğin, elit bir tabakadan ve güçlü olmasını ister. ıyi döşenmiş ve güvenliği sağlanmış bir ev, güzel giyecekler ve misafirlerine sunabileceği nadide yiyecekler Domuz kadınının mutluluğunda önemli role sahiptir. Domuz erkeğinin iş yaşamıyla alakalı büyük hedefleri vardır. Ancak duygusallığa yatkın doğası, büyük hedeflere ulaşamamasındaki en önemli etkendir. Zaman zaman kendi duygusallığından ürken Domuz erkeği, kendini ne kadar kontrol etmeye çalışsa da yine de hisler dünyasından kopamaz.";
    String unknown = "Burcunuzu tespit edemedik. Doğum tarihinizi doğru girdiğinizden emin misiniz? Lütfen tekrar deneyin.";

    //Facebook Audience Network
    private AdView adView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_fourth, container, false);

        // Premium & Facebook Audience Network
        premium = MainActivity.premium;
        if (premium) {
            //Do nothing
        } else {
            RelativeLayout adViewContainer = (RelativeLayout) rootView.findViewById(R.id.adFacebook);
            adView = new com.facebook.ads.AdView(getActivity(), "155235578298611_155235834965252", AdSize.BANNER_HEIGHT_50);
            AdSettings.addTestDevice("90ff5bfeac54391d98cc2bb9ff05ebb7");
            adViewContainer.addView(adView);
            adView.loadAd();
        }

        /* Colored bars */
        mContext = getActivity().getApplicationContext();
        window = getActivity().getWindow();
        actionbar = ((MainActivity) getActivity()).getSupportActionBar();
        coloredBars(ContextCompat.getColor(mContext, R.color.colorMainDark), ContextCompat.getColor(mContext, R.color.colorMainPrimary));

        // Analytics
        Tracker t = ((AnalyticsApplication) getActivity().getApplication()).getDefaultTracker();
        t.setScreenName("Çin astrolojisi");
        t.enableAdvertisingIdCollection(true);
        t.send(new HitBuilders.ScreenViewBuilder().build());

        intent = new Intent(getActivity(), CinAstrolojisiActivity.class);
        imagebutton = (ImageView) rootView.findViewById(R.id.imageView2);
        imagebutton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Calendar mcurrentTime = Calendar.getInstance();

                final DatePickerDialog datePicker;
                datePicker = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {

                    @SuppressWarnings("deprecation")
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                        Date date = new Date(year, monthOfYear, dayOfMonth);

                        Date date1925b = new Date(1924, 1, 5);
                        Date date2020a = new Date(2020, 0, 24);

                        Date date1925 = new Date(1925, 0, 23);
                        Date date1926 = new Date(1926, 1, 12);
                        Date date1927 = new Date(1927, 1, 1);
                        Date date1928 = new Date(1928, 0, 22);
                        Date date1929 = new Date(1929, 1, 9);
                        Date date1930 = new Date(1930, 0, 29);
                        Date date1931 = new Date(1931, 1, 16);
                        Date date1932 = new Date(1932, 1, 5);
                        Date date1933 = new Date(1933, 0, 25);
                        Date date1934 = new Date(1934, 1, 13);
                        Date date1935 = new Date(1935, 1, 3);
                        Date date1936 = new Date(1936, 0, 23);

                        Date date1937 = new Date(1937, 1, 10);
                        Date date1938 = new Date(1938, 0, 30);
                        Date date1939 = new Date(1939, 1, 18);
                        Date date1940 = new Date(1940, 1, 7);
                        Date date1941 = new Date(1941, 0, 26);
                        Date date1942 = new Date(1942, 1, 14);
                        Date date1943 = new Date(1943, 1, 4);
                        Date date1944 = new Date(1944, 0, 24);
                        Date date1945 = new Date(1945, 1, 12);
                        Date date1946 = new Date(1946, 1, 1);
                        Date date1947 = new Date(1947, 0, 21);
                        Date date1948 = new Date(1948, 1, 9);

                        Date date1949 = new Date(1949, 0, 28);
                        Date date1950 = new Date(1950, 1, 16);
                        Date date1951 = new Date(1951, 1, 5);
                        Date date1952 = new Date(1952, 0, 26);
                        Date date1953 = new Date(1953, 1, 13);
                        Date date1954 = new Date(1954, 1, 2);
                        Date date1955 = new Date(1955, 0, 23);
                        Date date1956 = new Date(1956, 1, 11);
                        Date date1957 = new Date(1957, 0, 30);
                        Date date1958 = new Date(1958, 1, 17);
                        Date date1959 = new Date(1959, 1, 7);
                        Date date1960 = new Date(1960, 0, 27);

                        Date date1961 = new Date(1961, 1, 14);
                        Date date1962 = new Date(1962, 1, 4);
                        Date date1963 = new Date(1963, 0, 24);
                        Date date1964 = new Date(1964, 1, 12);
                        Date date1965 = new Date(1965, 1, 1);
                        Date date1966 = new Date(1966, 0, 20);
                        Date date1967 = new Date(1967, 1, 8);
                        Date date1968 = new Date(1968, 0, 29);
                        Date date1969 = new Date(1969, 1, 16);
                        Date date1970 = new Date(1970, 1, 5);
                        Date date1971 = new Date(1971, 0, 26);
                        Date date1972 = new Date(1972, 1, 14);

                        Date date1973 = new Date(1973, 1, 2);
                        Date date1974 = new Date(1974, 0, 22);
                        Date date1975 = new Date(1975, 1, 10);
                        Date date1976 = new Date(1976, 0, 30);
                        Date date1977 = new Date(1977, 1, 17);
                        Date date1978 = new Date(1978, 1, 6);
                        Date date1979 = new Date(1979, 0, 27);
                        Date date1980 = new Date(1980, 1, 15);
                        Date date1981 = new Date(1981, 1, 4);
                        Date date1982 = new Date(1982, 0, 24);
                        Date date1983 = new Date(1983, 1, 12);
                        Date date1984 = new Date(1984, 1, 1);

                        Date date1985 = new Date(1985, 1, 19);
                        Date date1986 = new Date(1986, 1, 8);
                        Date date1987 = new Date(1987, 0, 28);
                        Date date1988 = new Date(1988, 1, 16);
                        Date date1989 = new Date(1989, 1, 5);
                        Date date1990 = new Date(1990, 0, 26);
                        Date date1991 = new Date(1991, 1, 14);
                        Date date1992 = new Date(1992, 1, 3);
                        Date date1993 = new Date(1993, 0, 22);
                        Date date1994 = new Date(1994, 1, 9);
                        Date date1995 = new Date(1995, 0, 30);
                        Date date1996 = new Date(1996, 1, 18);

                        Date date1997 = new Date(1997, 1, 6);
                        Date date1998 = new Date(1998, 0, 27);
                        Date date1999 = new Date(1999, 1, 15);
                        Date date2000 = new Date(2000, 1, 4);
                        Date date2001 = new Date(2001, 0, 23);
                        Date date2002 = new Date(2002, 1, 11);
                        Date date2003 = new Date(2003, 0, 31);
                        Date date2004 = new Date(2004, 0, 21);
                        Date date2005 = new Date(2005, 1, 8);
                        Date date2006 = new Date(2006, 0, 28);
                        Date date2007 = new Date(2007, 1, 17);
                        Date date2008 = new Date(2008, 1, 6);

                        Date date2009 = new Date(2009, 0, 25);
                        Date date2010 = new Date(2010, 1, 13);
                        Date date2011 = new Date(2011, 1, 2);
                        Date date2012 = new Date(2012, 0, 22);
                        Date date2013 = new Date(2013, 1, 9);
                        Date date2014 = new Date(2014, 0, 30);
                        Date date2015 = new Date(2015, 1, 18);
                        Date date2016 = new Date(2016, 1, 7);
                        Date date2017 = new Date(2017, 0, 27);
                        Date date2018 = new Date(2018, 1, 15);
                        Date date2019 = new Date(2019, 1, 4);
                        Date date2020 = new Date(2020, 0, 24);

                        // 5 Şubat 1924'ten küçük tarihler için
                        if (date.before(date1925b)) {
                            intent.putExtra("burc", "Burcunuzu bulamadık?");
                            intent.putExtra("burcyazisi", unknown);
                            intent.putExtra("burciconu", R.drawable.cin_unknown);
                        }
                        // Döngü 1
                        else if (date.before(date1925)) {
                            intent.putExtra("burc", "Fare");
                            intent.putExtra("burcyazisi", fare);
                            intent.putExtra("burciconu", R.drawable.cin_fare);
                        } else if (date.before(date1926)) {
                            intent.putExtra("burc", "Öküz");
                            intent.putExtra("burcyazisi", okuz);
                            intent.putExtra("burciconu", R.drawable.cin_okuz);
                        } else if (date.before(date1927)) {
                            intent.putExtra("burc", "Kaplan");
                            intent.putExtra("burcyazisi", kaplan);
                            intent.putExtra("burciconu", R.drawable.cin_kaplan);
                        } else if (date.before(date1928)) {
                            intent.putExtra("burc", "Tavşan");
                            intent.putExtra("burcyazisi", tavsan);
                            intent.putExtra("burciconu", R.drawable.cin_tavsan);
                        } else if (date.before(date1929)) {
                            intent.putExtra("burc", "Ejderha");
                            intent.putExtra("burcyazisi", ejderha);
                            intent.putExtra("burciconu", R.drawable.cin_ejdarha);
                        } else if (date.before(date1930)) {
                            intent.putExtra("burc", "Yılan");
                            intent.putExtra("burcyazisi", yilan);
                            intent.putExtra("burciconu", R.drawable.cin_yilan);
                        } else if (date.before(date1931)) {
                            intent.putExtra("burc", "At");
                            intent.putExtra("burcyazisi", at);
                            intent.putExtra("burciconu", R.drawable.cin_at);
                        } else if (date.before(date1932)) {
                            intent.putExtra("burc", "Keçi");
                            intent.putExtra("burcyazisi", keci);
                            intent.putExtra("burciconu", R.drawable.cin_keci);
                        } else if (date.before(date1933)) {
                            intent.putExtra("burc", "Maymun");
                            intent.putExtra("burcyazisi", maymun);
                            intent.putExtra("burciconu", R.drawable.cin_maymun);
                        } else if (date.before(date1934)) {
                            intent.putExtra("burc", "Horoz");
                            intent.putExtra("burcyazisi", horoz);
                            intent.putExtra("burciconu", R.drawable.cin_horoz);
                        } else if (date.before(date1935)) {
                            intent.putExtra("burc", "Köpek");
                            intent.putExtra("burcyazisi", kopek);
                            intent.putExtra("burciconu", R.drawable.cin_kopek);
                        } else if (date.before(date1936)) {
                            intent.putExtra("burc", "Domuz");
                            intent.putExtra("burcyazisi", domuz);
                            intent.putExtra("burciconu", R.drawable.cin_domuz);
                        }

                        // Döngü 2
                        else if (date.before(date1937)) {
                            intent.putExtra("burc", "Fare");
                            intent.putExtra("burcyazisi", fare);
                            intent.putExtra("burciconu", R.drawable.cin_fare);
                        } else if (date.before(date1938)) {
                            intent.putExtra("burc", "Öküz");
                            intent.putExtra("burcyazisi", okuz);
                            intent.putExtra("burciconu", R.drawable.cin_okuz);
                        } else if (date.before(date1939)) {
                            intent.putExtra("burc", "Kaplan");
                            intent.putExtra("burcyazisi", kaplan);
                            intent.putExtra("burciconu", R.drawable.cin_kaplan);
                        } else if (date.before(date1940)) {
                            intent.putExtra("burc", "Tavşan");
                            intent.putExtra("burcyazisi", tavsan);
                            intent.putExtra("burciconu", R.drawable.cin_tavsan);
                        } else if (date.before(date1941)) {
                            intent.putExtra("burc", "Ejderha");
                            intent.putExtra("burcyazisi", ejderha);
                            intent.putExtra("burciconu", R.drawable.cin_ejdarha);
                        } else if (date.before(date1942)) {
                            intent.putExtra("burc", "Yılan");
                            intent.putExtra("burcyazisi", yilan);
                            intent.putExtra("burciconu", R.drawable.cin_yilan);
                        } else if (date.before(date1943)) {
                            intent.putExtra("burc", "At");
                            intent.putExtra("burcyazisi", at);
                            intent.putExtra("burciconu", R.drawable.cin_at);
                        } else if (date.before(date1944)) {
                            intent.putExtra("burc", "Keçi");
                            intent.putExtra("burcyazisi", keci);
                            intent.putExtra("burciconu", R.drawable.cin_keci);
                        } else if (date.before(date1945)) {
                            intent.putExtra("burc", "Maymun");
                            intent.putExtra("burcyazisi", maymun);
                            intent.putExtra("burciconu", R.drawable.cin_maymun);
                        } else if (date.before(date1946)) {
                            intent.putExtra("burc", "Horoz");
                            intent.putExtra("burcyazisi", horoz);
                            intent.putExtra("burciconu", R.drawable.cin_horoz);
                        } else if (date.before(date1947)) {
                            intent.putExtra("burc", "Köpek");
                            intent.putExtra("burcyazisi", kopek);
                            intent.putExtra("burciconu", R.drawable.cin_kopek);
                        } else if (date.before(date1948)) {
                            intent.putExtra("burc", "Domuz");
                            intent.putExtra("burcyazisi", domuz);
                            intent.putExtra("burciconu", R.drawable.cin_domuz);
                        }

                        // Döngü 3
                        else if (date.before(date1949)) {
                            intent.putExtra("burc", "Fare");
                            intent.putExtra("burcyazisi", fare);
                            intent.putExtra("burciconu", R.drawable.cin_fare);
                        } else if (date.before(date1950)) {
                            intent.putExtra("burc", "Öküz");
                            intent.putExtra("burcyazisi", okuz);
                            intent.putExtra("burciconu", R.drawable.cin_okuz);
                        } else if (date.before(date1951)) {
                            intent.putExtra("burc", "Kaplan");
                            intent.putExtra("burcyazisi", kaplan);
                            intent.putExtra("burciconu", R.drawable.cin_kaplan);
                        } else if (date.before(date1952)) {
                            intent.putExtra("burc", "Tavşan");
                            intent.putExtra("burcyazisi", tavsan);
                            intent.putExtra("burciconu", R.drawable.cin_tavsan);
                        } else if (date.before(date1953)) {
                            intent.putExtra("burc", "Ejderha");
                            intent.putExtra("burcyazisi", ejderha);
                            intent.putExtra("burciconu", R.drawable.cin_ejdarha);
                        } else if (date.before(date1954)) {
                            intent.putExtra("burc", "Yılan");
                            intent.putExtra("burcyazisi", yilan);
                            intent.putExtra("burciconu", R.drawable.cin_yilan);
                        } else if (date.before(date1955)) {
                            intent.putExtra("burc", "At");
                            intent.putExtra("burcyazisi", at);
                            intent.putExtra("burciconu", R.drawable.cin_at);
                        } else if (date.before(date1956)) {
                            intent.putExtra("burc", "Keçi");
                            intent.putExtra("burcyazisi", keci);
                            intent.putExtra("burciconu", R.drawable.cin_keci);
                        } else if (date.before(date1957)) {
                            intent.putExtra("burc", "Maymun");
                            intent.putExtra("burcyazisi", maymun);
                            intent.putExtra("burciconu", R.drawable.cin_maymun);
                        } else if (date.before(date1958)) {
                            intent.putExtra("burc", "Horoz");
                            intent.putExtra("burcyazisi", horoz);
                            intent.putExtra("burciconu", R.drawable.cin_horoz);
                        } else if (date.before(date1959)) {
                            intent.putExtra("burc", "Köpek");
                            intent.putExtra("burcyazisi", kopek);
                            intent.putExtra("burciconu", R.drawable.cin_kopek);
                        } else if (date.before(date1960)) {
                            intent.putExtra("burc", "Domuz");
                            intent.putExtra("burcyazisi", domuz);
                            intent.putExtra("burciconu", R.drawable.cin_domuz);
                        }

                        // Döngü 4
                        else if (date.before(date1961)) {
                            intent.putExtra("burc", "Fare");
                            intent.putExtra("burcyazisi", fare);
                            intent.putExtra("burciconu", R.drawable.cin_fare);
                        } else if (date.before(date1962)) {
                            intent.putExtra("burc", "Öküz");
                            intent.putExtra("burcyazisi", okuz);
                            intent.putExtra("burciconu", R.drawable.cin_okuz);
                        } else if (date.before(date1963)) {
                            intent.putExtra("burc", "Kaplan");
                            intent.putExtra("burcyazisi", kaplan);
                            intent.putExtra("burciconu", R.drawable.cin_kaplan);
                        } else if (date.before(date1964)) {
                            intent.putExtra("burc", "Tavşan");
                            intent.putExtra("burcyazisi", tavsan);
                            intent.putExtra("burciconu", R.drawable.cin_tavsan);
                        } else if (date.before(date1965)) {
                            intent.putExtra("burc", "Ejderha");
                            intent.putExtra("burcyazisi", ejderha);
                            intent.putExtra("burciconu", R.drawable.cin_ejdarha);
                        } else if (date.before(date1966)) {
                            intent.putExtra("burc", "Yılan");
                            intent.putExtra("burcyazisi", yilan);
                            intent.putExtra("burciconu", R.drawable.cin_yilan);
                        } else if (date.before(date1967)) {
                            intent.putExtra("burc", "At");
                            intent.putExtra("burcyazisi", at);
                            intent.putExtra("burciconu", R.drawable.cin_at);
                        } else if (date.before(date1968)) {
                            intent.putExtra("burc", "Keçi");
                            intent.putExtra("burcyazisi", keci);
                            intent.putExtra("burciconu", R.drawable.cin_keci);
                        } else if (date.before(date1969)) {
                            intent.putExtra("burc", "Maymun");
                            intent.putExtra("burcyazisi", maymun);
                            intent.putExtra("burciconu", R.drawable.cin_maymun);
                        } else if (date.before(date1970)) {
                            intent.putExtra("burc", "Horoz");
                            intent.putExtra("burcyazisi", horoz);
                            intent.putExtra("burciconu", R.drawable.cin_horoz);
                        } else if (date.before(date1971)) {
                            intent.putExtra("burc", "Köpek");
                            intent.putExtra("burcyazisi", kopek);
                            intent.putExtra("burciconu", R.drawable.cin_kopek);
                        } else if (date.before(date1972)) {
                            intent.putExtra("burc", "Domuz");
                            intent.putExtra("burcyazisi", domuz);
                            intent.putExtra("burciconu", R.drawable.cin_domuz);
                        }

                        // Döngü 5
                        else if (date.before(date1973)) {
                            intent.putExtra("burc", "Fare");
                            intent.putExtra("burcyazisi", fare);
                            intent.putExtra("burciconu", R.drawable.cin_fare);
                        } else if (date.before(date1974)) {
                            intent.putExtra("burc", "Öküz");
                            intent.putExtra("burcyazisi", okuz);
                            intent.putExtra("burciconu", R.drawable.cin_okuz);
                        } else if (date.before(date1975)) {
                            intent.putExtra("burc", "Kaplan");
                            intent.putExtra("burcyazisi", kaplan);
                            intent.putExtra("burciconu", R.drawable.cin_kaplan);
                        } else if (date.before(date1976)) {
                            intent.putExtra("burc", "Tavşan");
                            intent.putExtra("burcyazisi", tavsan);
                            intent.putExtra("burciconu", R.drawable.cin_tavsan);
                        } else if (date.before(date1977)) {
                            intent.putExtra("burc", "Ejderha");
                            intent.putExtra("burcyazisi", ejderha);
                            intent.putExtra("burciconu", R.drawable.cin_ejdarha);
                        } else if (date.before(date1978)) {
                            intent.putExtra("burc", "Yılan");
                            intent.putExtra("burcyazisi", yilan);
                            intent.putExtra("burciconu", R.drawable.cin_yilan);
                        } else if (date.before(date1979)) {
                            intent.putExtra("burc", "At");
                            intent.putExtra("burcyazisi", at);
                            intent.putExtra("burciconu", R.drawable.cin_at);
                        } else if (date.before(date1980)) {
                            intent.putExtra("burc", "Keçi");
                            intent.putExtra("burcyazisi", keci);
                            intent.putExtra("burciconu", R.drawable.cin_keci);
                        } else if (date.before(date1981)) {
                            intent.putExtra("burc", "Maymun");
                            intent.putExtra("burcyazisi", maymun);
                            intent.putExtra("burciconu", R.drawable.cin_maymun);
                        } else if (date.before(date1982)) {
                            intent.putExtra("burc", "Horoz");
                            intent.putExtra("burcyazisi", horoz);
                            intent.putExtra("burciconu", R.drawable.cin_horoz);
                        } else if (date.before(date1983)) {
                            intent.putExtra("burc", "Köpek");
                            intent.putExtra("burcyazisi", kopek);
                            intent.putExtra("burciconu", R.drawable.cin_kopek);
                        } else if (date.before(date1984)) {
                            intent.putExtra("burc", "Domuz");
                            intent.putExtra("burcyazisi", domuz);
                            intent.putExtra("burciconu", R.drawable.cin_domuz);
                        }

                        // Döngü 6
                        else if (date.before(date1985)) {
                            intent.putExtra("burc", "Fare");
                            intent.putExtra("burcyazisi", fare);
                            intent.putExtra("burciconu", R.drawable.cin_fare);
                        } else if (date.before(date1986)) {
                            intent.putExtra("burc", "Öküz");
                            intent.putExtra("burcyazisi", okuz);
                            intent.putExtra("burciconu", R.drawable.cin_okuz);
                        } else if (date.before(date1987)) {
                            intent.putExtra("burc", "Kaplan");
                            intent.putExtra("burcyazisi", kaplan);
                            intent.putExtra("burciconu", R.drawable.cin_kaplan);
                        } else if (date.before(date1988)) {
                            intent.putExtra("burc", "Tavşan");
                            intent.putExtra("burcyazisi", tavsan);
                            intent.putExtra("burciconu", R.drawable.cin_tavsan);
                        } else if (date.before(date1989)) {
                            intent.putExtra("burc", "Ejderha");
                            intent.putExtra("burcyazisi", ejderha);
                            intent.putExtra("burciconu", R.drawable.cin_ejdarha);
                        } else if (date.before(date1990)) {
                            intent.putExtra("burc", "Yılan");
                            intent.putExtra("burcyazisi", yilan);
                            intent.putExtra("burciconu", R.drawable.cin_yilan);
                        } else if (date.before(date1991)) {
                            intent.putExtra("burc", "At");
                            intent.putExtra("burcyazisi", at);
                            intent.putExtra("burciconu", R.drawable.cin_at);
                        } else if (date.before(date1992)) {
                            intent.putExtra("burc", "Keçi");
                            intent.putExtra("burcyazisi", keci);
                            intent.putExtra("burciconu", R.drawable.cin_keci);
                        } else if (date.before(date1993)) {
                            intent.putExtra("burc", "Maymun");
                            intent.putExtra("burcyazisi", maymun);
                            intent.putExtra("burciconu", R.drawable.cin_maymun);
                        } else if (date.before(date1994)) {
                            intent.putExtra("burc", "Horoz");
                            intent.putExtra("burcyazisi", horoz);
                            intent.putExtra("burciconu", R.drawable.cin_horoz);
                        } else if (date.before(date1995)) {
                            intent.putExtra("burc", "Köpek");
                            intent.putExtra("burcyazisi", kopek);
                            intent.putExtra("burciconu", R.drawable.cin_kopek);
                        } else if (date.before(date1996)) {
                            intent.putExtra("burc", "Domuz");
                            intent.putExtra("burcyazisi", domuz);
                            intent.putExtra("burciconu", R.drawable.cin_domuz);
                        }

                        // Döngü 7
                        else if (date.before(date1997)) {
                            intent.putExtra("burc", "Fare");
                            intent.putExtra("burcyazisi", fare);
                            intent.putExtra("burciconu", R.drawable.cin_fare);
                        } else if (date.before(date1998)) {
                            intent.putExtra("burc", "Öküz");
                            intent.putExtra("burcyazisi", okuz);
                            intent.putExtra("burciconu", R.drawable.cin_okuz);
                        } else if (date.before(date1999)) {
                            intent.putExtra("burc", "Kaplan");
                            intent.putExtra("burcyazisi", kaplan);
                            intent.putExtra("burciconu", R.drawable.cin_kaplan);
                        } else if (date.before(date2000)) {
                            intent.putExtra("burc", "Tavşan");
                            intent.putExtra("burcyazisi", tavsan);
                            intent.putExtra("burciconu", R.drawable.cin_tavsan);
                        } else if (date.before(date2001)) {
                            intent.putExtra("burc", "Ejderha");
                            intent.putExtra("burcyazisi", ejderha);
                            intent.putExtra("burciconu", R.drawable.cin_ejdarha);
                        } else if (date.before(date2002)) {
                            intent.putExtra("burc", "Yılan");
                            intent.putExtra("burcyazisi", yilan);
                            intent.putExtra("burciconu", R.drawable.cin_yilan);
                        } else if (date.before(date2003)) {
                            intent.putExtra("burc", "At");
                            intent.putExtra("burcyazisi", at);
                            intent.putExtra("burciconu", R.drawable.cin_at);
                        } else if (date.before(date2004)) {
                            intent.putExtra("burc", "Keçi");
                            intent.putExtra("burcyazisi", keci);
                            intent.putExtra("burciconu", R.drawable.cin_keci);
                        } else if (date.before(date2005)) {
                            intent.putExtra("burc", "Maymun");
                            intent.putExtra("burcyazisi", maymun);
                            intent.putExtra("burciconu", R.drawable.cin_maymun);
                        } else if (date.before(date2006)) {
                            intent.putExtra("burc", "Horoz");
                            intent.putExtra("burcyazisi", horoz);
                            intent.putExtra("burciconu", R.drawable.cin_horoz);
                        } else if (date.before(date2007)) {
                            intent.putExtra("burc", "Köpek");
                            intent.putExtra("burcyazisi", kopek);
                            intent.putExtra("burciconu", R.drawable.cin_kopek);
                        } else if (date.before(date2008)) {
                            intent.putExtra("burc", "Domuz");
                            intent.putExtra("burcyazisi", domuz);
                            intent.putExtra("burciconu", R.drawable.cin_domuz);
                        }

                        // Döngü 7
                        else if (date.before(date2009)) {
                            intent.putExtra("burc", "Fare");
                            intent.putExtra("burcyazisi", fare);
                            intent.putExtra("burciconu", R.drawable.cin_fare);
                        } else if (date.before(date2010)) {
                            intent.putExtra("burc", "Öküz");
                            intent.putExtra("burcyazisi", okuz);
                            intent.putExtra("burciconu", R.drawable.cin_okuz);
                        } else if (date.before(date2011)) {
                            intent.putExtra("burc", "Kaplan");
                            intent.putExtra("burcyazisi", kaplan);
                            intent.putExtra("burciconu", R.drawable.cin_kaplan);
                        } else if (date.before(date2012)) {
                            intent.putExtra("burc", "Tavşan");
                            intent.putExtra("burcyazisi", tavsan);
                            intent.putExtra("burciconu", R.drawable.cin_tavsan);
                        } else if (date.before(date2013)) {
                            intent.putExtra("burc", "Ejderha");
                            intent.putExtra("burcyazisi", ejderha);
                            intent.putExtra("burciconu", R.drawable.cin_ejdarha);
                        } else if (date.before(date2014)) {
                            intent.putExtra("burc", "Yılan");
                            intent.putExtra("burcyazisi", yilan);
                            intent.putExtra("burciconu", R.drawable.cin_yilan);
                        } else if (date.before(date2015)) {
                            intent.putExtra("burc", "At");
                            intent.putExtra("burcyazisi", at);
                            intent.putExtra("burciconu", R.drawable.cin_at);
                        } else if (date.before(date2016)) {
                            intent.putExtra("burc", "Keçi");
                            intent.putExtra("burcyazisi", keci);
                            intent.putExtra("burciconu", R.drawable.cin_keci);
                        } else if (date.before(date2017)) {
                            intent.putExtra("burc", "Maymun");
                            intent.putExtra("burcyazisi", maymun);
                            intent.putExtra("burciconu", R.drawable.cin_maymun);
                        } else if (date.before(date2018)) {
                            intent.putExtra("burc", "Horoz");
                            intent.putExtra("burcyazisi", horoz);
                            intent.putExtra("burciconu", R.drawable.cin_horoz);
                        } else if (date.before(date2019)) {
                            intent.putExtra("burc", "Köpek");
                            intent.putExtra("burcyazisi", kopek);
                            intent.putExtra("burciconu", R.drawable.cin_kopek);
                        } else if (date.before(date2020)) {
                            intent.putExtra("burc", "Domuz");
                            intent.putExtra("burcyazisi", domuz);
                            intent.putExtra("burciconu", R.drawable.cin_domuz);
                        }
                        // 5 şubat 2020'den büyük tarihler için
                        else if (date.after(date2020a)) {
                            intent.putExtra("burc", "Burcunuzu bulamadık?");
                            intent.putExtra("burcyazisi", unknown);
                            intent.putExtra("burciconu", R.drawable.cin_unknown);
                        }
                        if (premium) {
                            startActivity(intent);
                        } else {
                            showAds();
                        }
                    }
                }, mcurrentTime.get(Calendar.YEAR), mcurrentTime.get(Calendar.MONTH),
                        mcurrentTime.get(Calendar.DAY_OF_MONTH));
                datePicker.setTitle("Tarih Seçiniz");
                datePicker.setButton(DatePickerDialog.BUTTON_POSITIVE, "Ayarla", datePicker);
                datePicker.setButton(DatePickerDialog.BUTTON_NEGATIVE, "İptal", datePicker);
                datePicker.show();
            }
        });

        return rootView;
    }

    public void showAds() {
        if (MainActivity.interstitial.isLoaded()) {
            startActivity(intent);
            MainActivity.interstitial.show();
        } else {
            startActivity(intent);
        }
    }

    public void coloredBars(int color1, int color2) {
        if (android.os.Build.VERSION.SDK_INT >= 21) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(color1);
            actionbar.setBackgroundDrawable(new ColorDrawable(color2));
        } else {
            actionbar.setBackgroundDrawable(new ColorDrawable(color2));
        }
    }

    @Override
    public void onDestroy() {
        if (adView != null) {
            adView.destroy();
        }
        super.onDestroy();
    }
}