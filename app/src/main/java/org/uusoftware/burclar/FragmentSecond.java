package org.uusoftware.burclar;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

public class FragmentSecond extends Fragment {

    // Koç (Kadın) - X (Erkek)
    String kockoc = "Dışa dönük ve hareketli bu iki kişinin ilişkisi çok yoğun ve ateşli olacaktır. İki Koç bir araya geldiğinde etrafa kıvılcımlar yayılabilir. ıkisi de önderlik ruhu taşıdığı için ilişkide üstünlüklerini kabul ettirmeye çalışacaktır ve bu aralarında sorun yaratabilir. Eğer iki taraf da anlayışlı davranmazsa ilişki çok uzun soluklu olmayacaktır. Ararındaki güç savaşından kurtulabilirlerse birlikte çok eğlenebilirler. Seksueül anlamda çok uyumludurlar çünkü beklentileri hemen hemen aynıdır.";
    String kocboga = "Boğa erkeği o kadar alışkanlıklarına ve ev hayatına bağlıdır ki onu evin dışına çıkarmanız neredeyse imkansızdır. Bu da enerjik ve hareketli Koç kadınını kısa bir süre içinde sıkabilir. Koç kadını hiçbir zaman acele etmeyen Boğa erkeğini elinde tutmak için alışılmamış derecede sabırlı olmak zorundadır. Bu ilişkide iki tarafında kişiliğinden taviz vermesi gerekir. Bu da zaman içinde tarafları yıpratır ve ilişkinin bitmesine sebep olur.";
    String kocikizler = "Çocuk ruhlu bu erkek tam da Koç kadınına göre! ıdeal sayılabilecek bu ilişki birçok ortaklık içerir. ıki tarafın da hareketi ve yeniliği seviyor olması ilişkinin monotonlaşmasını engeller. ıkizler erkeği kendinde olmayıp Koç kadınında olan özelliklere hayran kalır. Koç kadınının ani iniş ve çıkışları ıkizler erkeğini tedirgin etse de aralarındaki seksüel uyum sayesinde iki taraf da hem ilişkiden hem cinsellikten keyelse if alır.";
    String kocyengec = "Yengeç erkeği duygusal, romantik ve içe dönük yapıya sahip bir aşıktır. Duygularını kolay kolay belli etmez ve yaslanabileceği sağlam bir omuz arar. Onunla iyi geçinmek kolaymış gibi görünse de aslında oldukça zordur. Koç kadının fazlasıyla duygusal bu erkeği anlaması biraz zor olabilir. Her iki taraf da seksüel anlamda arzuludur ve cinselliğe önem verir. Ancak ilişkide romantizm daima ön planda olmalıdır.";
    String kocaslan = "Aslan erkeği Koç kadınını büyüleyebilir! Romantik, yüce gönüllü ve sizi el üstünde tutacak bu erkekle çok iyi anlaşacağınız kesin. Daha tanıştığınız ilk anda birbirinizi yıllardır tanıyormuş gibi hissederseniz sakın şaşırmayın. Hareketli, dışa dönük, konuşkan Aslan erkeği ilişkide sadakat ve ilgi bekler ve sık sık pohpohlanmaya ihtiyaç duyar. Devamlı şımartılmak isteyen bu erkek için zaman zaman kişiliğinizden taviz vermeniz gerekebilir.";
    String kocbasak = "Koç ve Başak birbirinden çok farklı karakterler çizerler. Koç kadını dışa dönük, zeki, sözünü sakınmayan, girişken bir yapıdadır. Başak erkeği ise titiz, detaycı ve alışkanlıklarına bağlıdır. Koç kadınının başına buyruk ve aklına eseni yapan bir tutum içinde olması Başak erkeğinin eleştirilerine sebep olur. Eğer ortak ilgi alanları bulmayı başarabilirseniz o zaman bu ilişki yürüyebilir. Ama çok kolay bir ilişki olmayacağını en başından bilmelisiniz.";
    String kocterazi = "Terazi burcu hava grubuna ait bir burçtur ve değişkendir. Terazi erkeği de bir öyle bir böyledir. Bir gün size hayatının aşkı olduğunuzu söylerken ertesi gün ilgisiz tavırlarıyla sizi çıldırtabilir. Bu durum ona karşı güven sorunu yaşamanıza sebep olabilir. Ama yine de sıcakkanlı ve uyumlu tavırlarıyla Koç kadınını etkisi altına almayı başaracaktır. Birbirinize anlayış göstermeyi başarabilirseniz ilişkinizde uyumu yakalayabilirsiniz.";
    String kocakrep = "İşte asla sıkıcı olmayacak bir ilişki… Koç burcu yerinde duramayan yapısıyla Akrep’i etkiler. Akrep ise doğal çekiciliğiyle Koç kadınını kolayca etkisi altına alır. Her iki taraf da hayatı, yaşamayı ve eğlenmeyi sevdiği için ilişkileri oldukça renkli olur. Akrep seksüel açıdan kuvvetlidir ve cinselliğe düşkündür. Koç ve Akrep cinsel açıdan mükemmel bir uyum yakalayabilir. Ama Akrep erkeğinin son derece kıskanç olması ve Koç kadının kısıtlanmaktan hoşlanmayan yapısı ilişkide pürüzlerin ortaya çıkmasına sebep olabilir.";
    String kocyay = "Koç da, Yay da maceraya bayıldığına göre çok eğlenceli bir ilişki sizi bekliyor. Siz her açıdan çok uyumlu bir çelse ift olacaksınız. Her iki burç da ateş grubuna dahil. Yani ikiniz de zeki, girişken, hareketli insanlarsınız. Paylaşımcı yapınız, açık havada gezmekten hoşlanmanız, doğayı ve hayvanları sevmeniz sizi birbirinize daha da yaklaştıracak. ıki taraf da seksüel açıdan arzulu ve yaratıcı. Bu yüzden Yay erkeği kolay bağlanamayan özgür bir karakter gibi görünse de, size karşı koyamayacak.";
    String kocoglak = "İlişkinizin zor ve inişli çıkışlı olacağını en başından bilmelisiniz. Oğlak burcu erkeği iddialıdır ve kolay kolay taviz vermez. Sevdiği kişiye kolayca bağlansa da sizin aşırı hareketlerinizi anlamasını beklemeyin. Diğer taraftan Oğlak erkeği ailesine çok düşkündür ve çok tutumludur. Koç kadınının savurganlığı karşısında şaşkına dönebilir. Bu ilişkinin yürümesinin tek koşulu taraflardan birinin anlayışlı ve özverili olması… Eğer mutlu olmak için elinizden geleni yaparsanız ve saygınızı yitirmezseniz birlikte mutlu olabilirsiniz.";
    String kockova = "Tanıştığınız anda birbirinizden fazlasıyla etkilenip ilk görüşte aşık olabilirsiniz. Ancak ilişkinin ilerleyen günlerinde Kova erkeğinin duygusallığı size fazla gelebilir. Çünkü Kova erkekleri duygusaldır ve genellikle duygularını çok kolay else ifade ederler. Koç kadınları ise konuşmak yerine tavırlarıyla kendilerini ortaya koymayı seçerler. Yapılarınız fazlasıyla farklı olduğu için birbirinizi anlamanız kolay olmayacaktır ve birbirinizin beklentilerine cevap veremeyebilirsiniz.";
    String kocbalik = "Son derece mantıklı ve gerçekçi bir kadın ve hayalperest bir erkek… Ne yazık ki aranızda uyumlu bir ilişkinin olacağını söylemek zor. Balık erkeği, sevgilisinin her anlamda kendisine destek olmasını ister. Beklediği ilgiyi görmezse bile bunu dile getirmez. Kendisini kırdığınızda da sizi uyarmaz ve geri gelmemek üzere size terk eder. ıki burç seksüel açıdan da çok uyumlu değildir. Balık’ın duygusal yönü ağır bastığından Koç kadar aktelse if olmaz. Kısacası uzun vadeli ilişki şansları pek yoktur.";

    // Boğa (Kadın) - X (Erkek)
    String bogakoc = "Boğa burcu kadını ile Koç burcu erkeği arasında bazı önemli farklılıklar olmasına rağmen, uyumlu bir ilişki görülebilir. Koç burcu yasam gücünü ve enerjisini yönetir. Onun için her şeyden önce kendisi gelir. Sabırsız ve acelecidir, bazen bu yüzden başı belaya girer. Boğa insani ise Koç’un fırtınalı hayatında sakin bir liman gibidir. Koç mücadeleci yapısı ile zorlukların üstesinden gelse de bazen yorulur. Boğa burcunun sakinleştirici ses tonu ve yumuşak tavırları ona iyi gelecektir. Her iki taraf da seksüel açıdan oldukça şehvetlidir.";
    String bogaboga = "İki Boğa arasında oldukça uyumlu bir ilişki yaşanabilir. ıkisi de yaşamın zevklerine ve rahatlarına düşkün olduğundan, hayatı paylaşmak konusunda herhangi bir sorun yaşamazlar. Her iki tip de duygusal ve romantiktir. Seksüel yönden birbirlerini tamamlarlar. ılişkilerinin uyumlu bir evliliğe dönüşme olasılığı yüksektir. ıki Boğa da birbirine güvenebildiği için çok mutludur. Boğa burcu hiç bir zaman sevgilisinin ya da başka birinin güvenini boşa çıkarmayı hayal bile etmez. Tabii ki ilişkinin sıkıcı bir birlikteliğe dönüşme riskini de unutmamak gerekir.";
    String bogaikizler = "Bu iki burç arasında pek uyumlu bir ilişkinin olması söz konusu değildir. ıkizler değişken yapısı nedeniyle sürekli farklı bir yapıya bürünürken, kendini saplama almak isteyen ve risklerden hoşlanmayan Boğa bu duruma ayak uydurmakta zorlanır. Boğa kendini evinde güvende hissederken, ıkizler burcu erkeği sürekli dolaşmak ve yeni yerler keşfetmek peşindedir. Boğa, ıkizler’in koltukta iki saat uyuduktan sonra bile sabahın köründe kalkıp yeni işler peşinde koşabilmesine akıl erdiremez. ıkizler ise Boğa`nın nasıl bu kadar çok uyku uyuyabildiğine inanamaz. Bu ilişkinin sonu hüsrandır.";
    String bogayengec = "Boğa ile Yengeç arasında uyumlu bir ilişki yaşanabilir. Yengeç akıllı, duygusal, romantik ve duyarlıdır. Gerçekten birini sevdiğinde fedakarlıkta sınır tanımaz. Boğa da tutkulu, zarelse if ve sevecen tavırlarıyla Yengeç’in kalbini çalabilir. Yatakta, Boğa’nın erotik yapısı ve Yengeç`in derin duygusallığı birleşince tutkulu bir beraberlik ortaya çıkar. Bu ilişkinin yürümemesi için hiçbir sebep yoktur. Baş başa kaldığınız zaman kucak kucağa ve yanak yanağa sevgi dolu saatler geçirebilir; aile, ev, güvenlik, düzenli bir hayat ve çocuk hayalinize Yengeç erkeği ile kavuşabilirsiniz.";
    String bogaaslan = "Boğa ile Aslan burcunun arasında eğer gerçek sevgi yakalanabilirse mükemmel bir uyum söz konusu olabilir. ıki burcun da hayattan beklentileri aynıdır. şık bir restoranda yemek yemek, ilginç partilere, kültürel aktivitelere katılmak, güzel bir eve sahip olmak gibi hayatin ince, güzel, seçkin şeylerinden zevk alırlar. Aslan burcu huzuru ve huzurlu ortamlarda bulunmayı sever, Boğa burcu kadını bu anlamda Aslan erkeğini büyüleyebilir. Her iki taraf da seksüel yönden oldukça arzuludur, fakat zaman zaman Aslan burcunun bencilliği ön plana çıkabilir.";
    String bogabasak = "Boğa ve Başak arasında uyumlu, saygı ve sevgiye dayanan bir ilişki olabilir. Boğa kadınının sevecen, zarelse if ve uyumlu yapısı Başak erkeği için oldukça etkileyici olacaktır. Başak burcunun bazı tavırları ve aklından geçenleri direkt söylemesi zaman zaman Boğa kadınının kırılmasına neden olabilir. Ama bu onun kötü niyetinden değil içten olmasından kaynaklanır. Boğa ile Başak burcundaki insanların birbirine duydukları sevgi cinsel hayatlarında da uyumlu olmalarını sağlar.";
    String bogaterazi = "İki burç da Venüs tarafından yönetildiğinden aralarında uyumlu bir ilişki olma olasılığı yüksektir. ıki karakterin de özellikleri birbirine benzer. Hayat doludurlar ve yaşadıkları her andan keyelse if almak isterler. Özellikle Terazi duygusal, romantik tavırları ile Boğa’yı derinden etkiler. Terazi’nin sadık ve uyumlu olması Boğa’nın güven arayışını oldukça tatmin eder. ıki taraf da seksüel yönden arzulu ve duygusaldır. Eğer birbirlerine hassas oldukları konularda gerekli anlayışı göstermezlerse ilişkileri uzun soluklu olmayabilir.";
    String bogaakrep = "Boğa ve Akrep burcu karakter olarak birbirinden çok farklıdır. Zıt kutuplar birbirini çeker sözü bu iki burç için geçerli olabilir. Akrep tutkulu, akıllı, duygusal ve açık sözlüdür. Boğa’nın pozitelse if ve zarelse if yapısı ile Akrep burcunun tutkulu yapısı birbirini dengeleyebilirse bir ömür boyu mutlu bir beraberlik yakalanabilir. Her iki taraf da seksüel yönden oldukça tutkuludur. ıki burcun da birbirini üzebilecek en olumsuz yanı ikisinin de oldukça kıskanç olmasıdır.";
    String bogayay = "Boğa ile uzun birliktelik sağlayamayacak burçlardan bir diğeri de Yay’dır. Yay’ın ele avuca sığmayan, bağımsızlığına düşkün, hareketli tavırları, Boğa’nın derin duygularına hitap etmeyecektir. Kendini güvende hissetmek için maddi bazı gerekliliklere ihtiyaç duyan Boğa burcu için Yay burcunun parayı idare edememesi oldukça rahatsız edici olacaktır. Bu iki burcu çocuklara düşkün olmaları yakınlaştırsa da temelde pek bir uyum sergilemeleri zordur.";
    String bogaoglak = "Boğa burcunun duygusal derinliklerine hitap edebilecek burçlardan biri de Oğlak burcudur. Oğlak duygusal, düşünceli ve sabırlıdır. Bir kez sevdiği zaman hayatının sonuna kadar sevdiğinden ve ona bağlı kalmaktan vazgeçmeyecektir. ıkisi de toplumdaki statülerine önem verir, saygı duyulmak isterler ve aykırı, isyankar hareketlerde bulunmazlar. Birlikte çok uyumlu bir hayatları olacaktır. Oğlak burcu kafasını günlük işlerden uzaklaştırıp rahatlattıktan sonra seksüel anlamda Boğa kadar ateşli ve istekli olabilir.";
    String bogakova = "Kova’nın değişken yapısı Boğa’yı rahatsız eder. Ayrıca güvenlik konusunda oldukça hassas olan Boğa burçları için Kova’nın sadakat konusunda da pek tutarlı olmamaları kabul edilemez bir durumdur. Kova burcu tek bir kişiye bağlanmakta zorluk çeker. Boğa ise bunun tam tersidir. Kova ile Boğa romantiklik ve duygusallık açısından birbirinden çok farklı beklentileri olan tiplerdir. Ancak birbirlerini çok severlerse aralarındaki ilişki evliliğe dönüşebilir.";
    String bogabalik = "Boğa ile Balık burcunun ilişkisi birbirlerinden çok farklı olduklarından pek uyumlu olmaz. Balık’ın romantik, hayalperest ve aşırı duygusallığı Boğa için anlaşılmazdır. Balık burcunun bazı konulardaki vurdumduymazlığı Boğa burcunu oldukça rahatsız eder. Balık’ın ilgili ve duyarlı olması Boğa’ya yetmeyecektir. Balık için hayal edilebilen her şeyin gerçekleşmesi mümkündür, sınır tanımaz. Siz ise dokunulabilen gerçekleri yaratır ve kurarsınız. Bu iki tip birbirine çekici gelse de ilişkileri uzun ömürlü olmayacaktır.";

    // İkizler (Kadın) - X (Erkek)
    String ikizlerkoc = "Aranızda ateşli ve uyumlu bir ilişki olabilir. ıkizler kadınının yerinde duramayan, hareketli, zeki bir erkeğe karşı koyması imkansız gibidir. Birbirinizi anlamaya çalıştığınız ve birbinize saygı duyduğunuz sürece iyi geçinebilirsiniz. Fakat ıkizler kadınının başına buyruk halleri zaman zaman Koç erkeğinin kendini zor durumlar içinde bulmasına ve kıskançlık krizi yaşamasına sebep olabilir. ıkizler bu konuda hassas ve ince düşünceli davranmazsa birbirlerinden hızla uzaklaşırlar.";
    String ikizlerboga = "Zodyak’ta her burç bir önceki burcun eksikliğini tamamlar. ıkizler burcu Boğa’dan sonra gelir ve Boğa’nın eksikliklerini tamamlar. ıkizler kadını pratikliği ve enerjisiyle Boğa’yı destekler. Boğa erkeğinin ıkizler’in ruhsal duygu değişimlerini anlaması zor olsa da karşılıklı anlayış ile ilişkilerini rahatça devam ettirebilirler. Eğer cinsel açıdan da uyumu yakalarlarsa bir ömür boyu mutlu olabilirler. Seksüel açıdan Boğa burcu ıkizler’deh daha arzuludur. Eğer ıkizler de onun kadar tutkulu olmazsa ilişkileri tehlikeye girebilir.";
    String ikizlerikizler = "Bu iki karakter birbirine fazlasıyla benzer. ıki taraf da çok doludur ve zamanları yoktur. Ayrıca ikisi de sevecen, hareketli ve samimidir. Değişik ortamlara girmeyi ve yeni insanlarla tanışmayı severler. Özgürlükçü bir bakış açısına sahip oldukları için birbirlerine saygı duyarlar ve kısıtlamalar yapmazlar. Birçok konuda anlaştıkları için çok fazla tartışma ortamı olmaz ama bir süre sonra birbirlerine bu kadar benzemeleri ilişkinin sıkıcı hale gelmesine neden olabilir.";
    String ikizleryengec = "Yengeç erkeği için ıkizler kadını fazlasıyla neşeli ve hareketlidir. O, duygulu, çalışkan, sorumluluk sahibi ve başarıya önem veren bir yapıdadır ve bunun dışında evcimendir. Evi adeta onun kalesi gibidir. Kendini evinde rahat ve mutlu hisseder. Sevdiği kişiyi ilgisiyle ve sevgisiyle şımartmayı çok sever. Ona yeteri kadar ilgi ve zaman ayırmadığınızda kendisini sevmediğinizi düşünebilir. Eğer ona sadık kalır ve sevginizi yoğun bir şekilde idafe edebilirseniz onunla bir ömür boyu mutlu olabilirsiniz.";
    String ikizleraslan = "Pohpohlanmaya ihtiyaç duyan ve hiçbir zaman geri planda kalmak istemeyen Aslan erkeği, güvenilir ve sadık bir sevgilidir. ıkizler’in neşeli ve zeki tavırlarından kolayca etkilenen bu erkeğin sevdiği kişi için yapmayacağı fedakarlık yoktur. Koruma güdüleri gelişmiş ve çalışkan Aslan erkeği gösterişe ve lükse düşkündür. Aranızda uyumlu bir ilişki olabilir. Kişilik özellikleri açısından önemli bir zıtlık yaşamazsanız onunla birlikte çok mutlu olabilirsiniz.";
    String ikizlerbasak = "ikizler kadını konuşkan ve pratiktir. Başak erkeği ise gerçekçi ve mantıklı… Olaylara her zaman mantık çerçevesinden bakan bu erkek ile aranızda çok uyumlu bir birliktelik olmaz. Başak’ın her şeyi planlı ve programlı bir şekilde organize etmesi ıkizler’i eninde sonunda sıkacaktır. Bu ikili seksüel açıdan da uyumlu bir çelse ift oluşturmaz. ıkizler çok istekli ve tutkuludur, Başak erkeğinin ise öncelikleri başka olabilir.";
    String ikizlerterazi = "İki burç da hava grubundan olduğundan aralarında uyumlu bir ilişki olabilir. Genel anlamda ikisi de uçarı ve değişkendir. ıkisi de özgürlüğüne düşkündür. Bunun yanında Terazi erkeği daha sevecen ve romantiktir. ıkizler kadını ise zeki ve konuşkandır. Bu özellikleriyle Terazi’yi kolayca etkiler. Terazi detaycı olduğundan göz zevkine hitap edecek bakımlı kadınlardan hoşlanır. ıki taraf da seksüel açıdan arzulu ve tutkuludur. Eğer sadakat konusunda problem yaşamazlarsa eğlenceli bir birliktelik yaşayabilirler.";
    String ikizlerakrep = "Akrep erkeğinin kıskançlığı, ıkizler kadınını çileden çıkaracağı için ilişki biraz zor olacaktır. Ancak bu iki tip doğal cazibelerinden dolayı birbirlerini fazlasıyla çeker. Akrep erkeği duygusal, tutkulu ve çekicidir. Güven ve sadakat onun için çok önemlidir. Güveninin suistimal edildiğini hissederse karşı tarafa acı çektirmek için elinden geleni yapar. Bu ilişkinin yürüyebilimsei için ıkizler’in Akrep’in hükmedici tavırlarınıdan rahatsızlık duymaması çok önemlidir. Aksi halde ilişki kısa soluklu olabilir.";
    String ikizleryay = "İkizler kadını pratik zekası ve neşeli tavırlarıyla Yay erkeğini kolayca etkiler. Yay erkeği de konuşkan ve dışa dönük oluşuyla ele avuca sığmayan bir karakter çizer. ıkizler ve Yay genel anlamda iyi anlaşır ve uyumlu bir ikili olur. Ancak uzun vadede bağımsız kişiliklerinden dolayı birbirlerinden kopma ihtimalleri çok yüksektir. ılişkide iki taraf da bağlanmakten korkar. Aşk ilişkileri bitse bile dostlukları mutlaka devam edecektir.";
    String ikizleroglak = "İkizler ve Oğlak arasında pek uyumlu bir ilişki söz konusu olmaz. Oğlak erkeği, ıkizler kadınını çekecek özelliklere sahip değildir. Özellikle huzursuz ve sinirli yapısı ıkizler’i fazlasıyla rahatsız edebilir. Oğlak’ın kıskanç tavırları ıkizler’in kendini kısıtlanmış hissetmesine neden olur. Arkadaş olarak çok iyi anlaşacak bi ikili, sevgili olunca ancak aralarındaki aşkın çok kuvvetli olması şartıyla ilişkiyi yürütebilir. Aksi halde bu ilişki oldukça zor olacaktır.";
    String ikizlerkova = "Her iki burç da hava grubundandır ve aralarında uyumlu bir ilişki olacaktır. ıkizler kadını gibi Kova erkeği de değişken ve neşelidir. Ayrıca ikisi de özgürlüğüne düşkündür ve kısıtlanmaktan hoşlanmaz. Bu iki tip birbirini oldukça çekici bulur ve iyi arkadaşlık kurar. Seksüel açıdan da oldukça uyumludurlar. ıstek ve arzularını birbirlerine else ifade etmekten çekinmezler";
    String ikizlerbalik = "İlk başta hareketli ve heyecan verici bir birliktelik gibi görünse de ıkizler ve Balık arasında pek uyumlu bir ilişki olmaz. ıkizler kadını, dışa dönük ve konuşkandır. Balık erkeği ise romantik, duygusal ve hayalperesttir. Balık erkeği, pek konuşkan değildir ve ıkizler burcunun ilgisini çekebilecek konular hakkında konuşmayı pek beceremez. Seksüel yönden de birbirlerinin isteklerini karşılayamazlar.";

    // Yengeç (Kadın) - X (Erkek)
    String yengeckoc = "Yengeç kadını ile Koç erkeğinin çok uyumlu olması beklenemez. Yengeç kadınlarının hassas, duygusal ve romantik yapısı ve duygusal derinliği, Koç erkeğinin hareketli, dışa dönük ve özgürlüğüne düşkün olması ile çatışabilir. Koç burcu erkeği, daha çok duygularını dışa vurmaktan hoşlanmayan Yengeç için zaman zaman kırıcı olabilir. Koç burcu seksüel açıdan arzulu ve tutkuludur, Yengeç’in önceliğinde ise duygusallık ve romantizm vardır. ıki burç arasında yoğun bir aşk olsa da uyumlu bir birliktelik çok zordur.";
    String yengecboga = "Bu iki burç arasında uyumlu bir ilişki söz konusu olabilir. ıki taraf da duygusal derinliğe, kendini güvende hissetmeye ve sadakate önem verir. Maddi konularda iki taraf da çalışkan ve tutumludur. Aralarında sorun olabilecek konulardan biri Boğa erkeğinin fazlasıyla kıskanç olmasıdır. Yengeç kadını bu durumu anlayamayacak ve çıkan huzursuzluktan rahatsız olacaktır. Seksüel yönden iki taraf adeta birbirini tamamlarlar. Uzun ve mutlu bir birliktelikleri olabilir";
    String yengecikizler = "İkizler burcunun değişken yapısı ve hareketli olması, duygusal ve romantik Yengeç için ilk başta ilgi çekici olsa da uzun vadede bir ilişki kurmasına en büyük engel haline gelecektir. Yengeç burcunun bazı duygusal tarafları, kendisi ile bile dalga geçen ıkizler burcuna komik gelecektir. Kötü niyetli olmasa da ıkizler’in mantıklı tarafı Yengeç kadınının kırılgan yapısını öne çıkartabilir. Yengeç kadınının evinde kendini güvende hissetmesine karşı ıkizler erkeği bazen eve girmek istemeyebilir. Diğer yandan seksüel olarak da beklentileri farklı olacaktır.";
    String yengecyengec = "Yengeçler arasında uyumlu bir ilişki olur. Yengeç akıllı, duygusal, romantik ve sağduyuludur. Yengeç kadını kendine bir ömür boyu rahat ve mutlu hissedeceği bir ortam hazırlar. Mutlu bir ev hayatının olmasını ister. Birbirine sadık ve eğlenceli birer partner olurlar. Seksüel yönden iki taraf da arzulu ve tutkuludur. Duygusal açıdan da birbirlerine hitap ederler. Eğer kişisel problemleri ön plana çıkmazsa birlikte uyumlu ve mutlu bir birliktelik sürdürebilirler.";
    String yengecaslan = "Bu beraberlik klasik anlamda uyumlu sayılmasa da iyi olabilir. Aslan erkeği Yengeç kadına göre oldukça farklılık gösterebilir. Aslan hayata tutku ile bağlıdır ve istek doludur. Zaman zaman benmerkezci ve egoist de olabilir. Hayran olunmak ve saygı duyulmak Aslan’ın en büyük ihtiyacıdır. Kötü günler için bir kenarda para biriktirmek aklının ucundan bile geçmez. Yengeç burcu ise parasını gelecek kaygısı ile biriktirir hatta istelse if eder. Aslan’ın aldığı pahalı hediyeler Yengeç kadınının başını döndürebilir. Seksüel yönden iki taraf da arzulu ve tutkuludur.";
    String yengecbasak = "Yengeç kadını ile Başak erkeğinin pek uyumlu ilerleyen bir birlikteliği olmayacaktır. Yengeç burcunun duygusallığına Başak burcunun mantıklı yaklaşımı iki tarafın da beklentilerinin karşılanmamasına neden olabilir. Başak burcunun prensipleri ve bu prensiplere olan bağlılığı Yengeç kadını için zorlayıcı bir unsurdur. Aralarında yaşanacak iletişim kopuklukları geniş zaman diliminde birliktelikleri için ciddi sorunlar yaratabilir. Yengeç burcu kadını her en kadar iyi niyetli davransa da, Başak erkeğinin temposuna ayak uyduramayacağı için ilişki sonunda mutsuz olabilir.";
    String yengecterazi = "Yengeç kadını Terazi erkeğinin hoş, çekici ve dayanılmaz cazibesinin karşısında kayıtsız kalamaz. Ancak Terazi`nin sürekli dış dünya ile ilişki içinde olması, Yengeç’in daha içe dönük ve ailesine bağlı yapısı aralarında sorun oluşturabilir. Terazi erkeğinin ise eğlenceye ve insanlara ihtiyacı vardır. Terazi burcu geleceğini pek düşünmez ya da düşündüğü kadarı Yengeç kadını için yeterli olmayabilir. Uzun vadede çıkan sorunlara ortaklaşa çözüm bulunmazsa bu ilişkinin ömrü kısa olabilir.";
    String yengecakrep = "Yengeç kadını ile Akrep erkeği arasında son derece seksi ve sağlam bir ilişki olabilir. Yengeç kadınının korunma ve güvenilir bir yerde kendini hissetme arzusu kendisini Akrep erkeğinin kollarına bırakmakla sonuçlanacaktır. Birlikte bütün bir günü yatakta geçirebilirsiniz! Akrep erkeği ile Yengeç kadınının istekleri hemen hemen aynıdır: Ev hayatı, kendine ait özel hayat, çocuklar ve hayatın sonuna kadar harika bir seks! Akrep biraz sabit fikirlidir. Onunla bir çok konuda konuşarak sorunlarınızı halledebilirsiniz.";
    String yengecyay = "Yengeç kadını ile Yay erkeğinin bir araya gelmesi aslında çok da iyi bir fikir olmayabilir. Yay, iyi eğitim almış, kültürlü, değişik ilgi alanları olan biridir. Onun ilgi çekici konuşması Yengeç kadınının dikkatini çekse de gelecekte pek de uyumlu bir birliktelik olmayabilir. Yengeç burcu sezgilerinize göre hareket ederken, Yay akılcı ve analitiktir. Yay zaman zaman kibirli, küstah ve nezaketsiz olabilir. Yatakta ise Yay eğlenceli ve atletiktir, Yengeç kadını ise derin ve duygusaldır. Bu iki burç ilk başta birbirlerinden etkilenseler de gelecekleri yoktur.";
    String yengecoglak = "Oğlak’ın hırslı ve başarıya odaklı yapısı Yengeç burçları ile iyi anlaşmalarına neden olur. Yengeçler de bu içgüdüye sahip olduğu için ilişkileri de verimli ve uzun olur. Oğlak erkekleri pratik, sağlam ve güvenilirdir. Kazandıklarını yatırımlarla değerlendirir, Yengeçler de birikim yapmayı sevdiğinden bu konuda oldukça iyi anlaşırlar. Yengeç kadını bu ilişkiye sezgilerini, duygusallığını katar. Bu iki burç yatakta da birbirleriyle çok uyumludur. Oğlak erkeğinin birçok hayranı olsa da kaçamak yapmayacak, ilişkisine sadık kalacaktır. Bu da Yengeç kadınının ihtiyacı olan güven duygusunu yaratacaktır.";
    String yengeckova = "Yengeç kadını ile Kova erkeği ayrı dünyaların insanlarıdır. Kova insanı, hayatı araştırmacı bir bilim adamı gibi yasar. Verileri toplayıp mantıklı sonuçlar çıkarmaya çalışır. Yengeç ise kalbinin sesinin onun hep doğru yöne götürdüğünü düşünür ve ona göre hareket eder. Kova erkeği ilişkisinde mesafeli bir tavır sergileyebilir. Başta onun bu ilgisiz havası gizemli gelip Yengeç kadınını çekse de zamanla kırıcı bir hal alabilir. Kova bireyseldir, bir çelse ift gibi hissedemez ve düşünemez. Bu yüzden de ilişkideki verici taraf her zaman Yengeç olacaktır. ıki burcun ilişkisi heyecanlı başlasa da hüsranla bitebilir.";
    String yengecbalik = "Balık, Yengeç kadınlarının en çabuk aşık olduğu burçlardan biridir. Balık burcunun sezgileri de Yengeç burcu kadar gelişmiştir. Zodyak`ın sonuncu burcu olan Balık kendinden önce gelen bütün burçlara anlayış gösterir. Sadece yüzeyde görüneni değil, altında yatan gerçekleri de görmede ustadır. Balık erkeği ile Yengeç kadını aynı beklentilere sahiptir. Düzgün bir ev hayatı, çocuklar, yaratıcılığını else ifade edebilme fırsatı onlar için önemlidir. Balık erkeği ile Yengeç kadınının ilişkisi uyum ve aşk içinde ilerleyecektir. Balık erkeği aşkının gücü ile Yengeç kadınını kendine bağlayacaktır.";

    // Aslan (Kadın) - X (Erkek)
    String aslankoc = "Son derece dinamik, büyük enerjiler ortaya çıkarabilecek bir birliktelik… Aslan hareketli, neşeli ve girişken bir kadındır. Koç erkeği de dinamik ve kendine güvenen bir yapıdadır. Koç erkeği zaman zaman ilişkilerinde sadakat problemi yaşar. Eğer bu durumun önüne geçebilirseniz ilişkiniz arzulu ve tutkulu olacaktır. Aslan’ın hükmetmeyi seven yapısı da bu ilişkide zorlayıcı bir eken olabilir.";
    String aslanboga = "Aslan kadını neşeli, girişken, romantik ve gururludur. Boğa erkeği de aynı şekilde duygusal ve hassas bir yapıdadır. Boğa’nın hareketli ve dışa dönük yapısı Aslan’ın kıskançlık sınırlarını zorlayabilir. Eğer Boğa, övgülerle Aslan’ın gururunu okşar ve onun güvenini kazanmayı başarırsa ilişkileri uyumlu bir şekilde yol alabilir.";
    String aslanikizler = "İkizler erkeği tam Aslan kadınına göredir. Konuşkan ve girişken yapısıyla kolayca dikkat çeker. Ayrıca neşeli ve romantiktir. Espiritüel yaklaşımıyla Aslan kadınını kolayca etkiler. ılişkinin devam edebilmesi içinse Aslan’ın sahiplenici ve kıskanç tavırlarına hoşgörüyle yaklaşması şarttır. Seksüel açıdan iki taraf da arzulu ve isteklidir. Uzun vadede çok uyumlu ve faal bir birliktelik yaşayabilirler.";
    String aslanyengec = "Yengeç erkeğinin kendinden emin davranışlarının ardında güvensiz bir ruh yatar. Aslan kadınının güvenini kazanıp, onu açması uzun sürecektir. Aslan kadını onun kalbini fethetmeyi başarırsa, Yengeç erkeği ona karşı içten ve sadık davranacaktır. Yengeç erkeği evcimendir, Aslan ise dışa dönük ve gezmeyi seven bir tiptir. Sonuç olarak ilişkinin yürümesi için birbirlerini çok iyi anlayabilmeleri ve hoşgörülü davranmaları gerekir.";
    String aslanaslan = "İki Aslan arasında çok uyumlu bir ilişki olabileceği gibi tamamen uyumsuz bir ilişki de olabilir. Hem kadın, hem erkek burç ateş grubudur ve aynı burçtan kadın ve erkeğin birçok ortak noktası olabilir. Her ikisi de hareketli, neşeli, girişken ve romantiktir. Hayatın güzelliklerine, gösterişe ve lükse oldukça düşkündür. Aynı zamanda da gururludur. Birbirlerine anlayış gösterdikleri ve karşı tarafı baskı altına almaya çalışmadıkları müddetçe ilişkileri yürüyebilir.";
    String aslanbasak = "Aslan kadını hareketli, neşeli, girişken ve romantiktir. Hayatın güzelliklerine, gösterişe ve lükse oldukça düşkündür. Aynı zamanda da gururludur. Başak erkeği ise sevecen, iyi niyetli, güvenilir ve detaycıdır. Bir kez sevdiği zaman güvenilir ve sadık bir aşık olacaktır. ıkisi arasındaki ilişkinin çok uyumlu olacağı söylenemez ama birbirlerine anlayış gösterirlerse iyi bir ilişkileri olabilir.";
    String aslanterazi = "Terazi erkeği neşelidir ve Aslan kadınına çok iyi gelir. Aslan kadını da neşeli, girişken ve romantik tavırlarıyla Terazi erkeğini kolayca etkisi altına alır. Bu iki burcun insanı aşk hayatında çok iyi anlaşacakları gibi iki iyi de arkadaş olacaktır. Tek sorun Aslan burcunun gururunun ön plana çıkması olabilir. Bu durumda da Terazi’nin dengeleyici bir tutum içine girmesi gerekir.";
    String aslanakrep = "Akrep erkeğinin sihirli çekim gücü Aslan kadınını kolayca etkisi altına alacaktır. ıkisi arasında uyumlu ve tutkulu bir ilişki olabilir. Yalnız Akrep’in ikili ilişkilerde fazlaca kıskanç olması ilişkinin yıpratıcı olmasına ve zarar görmesine sebep olabilir. Aslan kadını da Akrep’i baskı altına almaya çalışmamalı ve ona hükmetmeye kalkmamalıdır. Çünkü beklemediği kadar sert bir tepkiyle karşılaşabilir.";
    String aslanyay = "Yay erkeği, her zaman macera ve meydan okuma arayışındadır. Gittiği her yerde gözü pek davranışlarda bulunabilir. Cesaret ve atılganlığı Aslan kadınının oldukça hoşuna gider. Bunun yanı sıra, sorumsuzluk ve duyarsızlığı da söz konusudur. ıki ayrı mizaca sahip bu iki kişi, şaşırtıcı bir şekilde çok iyi anlaşabilir. Hatta uzun vadede evliliğe dönüşebilecek uyumlu bir birliktelik olacaktır.";
    String aslanoglak = "Cömert ve aristokrat Aslan ile hırslı ve işkolik Oğlak burçlarının birlikteliği mümkün, ancak iki tarafında da elinden geleni yapması şart. Aslan kadını romantik, neşeli, girişken ve lüks meraklısıdır. Oğlak erkeği ise Satürn tarafından yönetilir, tedbirlidir ve hayatı çok ciddiye alır. ıki burcun insanı çok farklı olsa da birbirine iyi gelir. Seksüel açıdan da birbirlerinin isteklerini karşılarlar. Biraz çabayla uzun vadede uyumlu bir ritm yakalayabilirler.";
    String aslankova = "Kova erkeğinin sevgi konusundaki ısrarı ve sabrı, Aslan kadınını etkiler. Kova erkeği idealisttir, belirgin bir duyarlılığı vardır, bu nedenle birbirlerini tamamlayacaklardır. Aslan kadını, onu ütopyalar aleminden alarak, gerçekler dünyasına indirecek kişidir. Birbirlerinden her durumda çok şey öğrenebilirler. Ortak ve farklı noktaları onları dengede tutar. Kova’nın Aslan’ın gururunu sürekli okşaması ve ikilinin kişilik çatışmalarından uzak durmaları şartıyla ilişkileri evliliğe kadar gidebilir.";
    String aslanbalik = "Aslan ve Balık birbirinden çok farklı iki burçtur ve bir araya gelmeleri oldukça zordur. Aslan kadını hareketli, neşeli ve girişkendir. Balık erkeği ise duygusal, hassas ve romantiktir. Balık burcu ince düşünür ve hayalperesttir. Aslanlar ise başkalarının duygularına fazla önem vermezler. Yine de birbirlerine birçok konuda destek olacakları düşünülürse, kısa vadeli ve tutkulu bir birliktelik yaşayabilirller.";

    // Başak (Kadın) - X (Erkek)
    String basakkoc = "Başak burcu kadını ile Koç burcu erkeği arasında uyumlu bir ilişki olabilir. Sevecen, zarelse if ve içten tavırlarıyla Başak burcu, Koç burcunu derinden etkilemeyi başarır. Başak akıllı, sevecen, iyi niyetli, güvenilir ve detaycıdır. Sevdiğine karşı bağlı ve sadıktır. Ancak tüm bu özellikler onu Koç burcuna göre daha duygusal yapar. Koç zeki, girişken, neşeli, biraz da ayran gönüllüdür. Hayatı dolu dolu yaşamayı sever ve özgürlüğünden de ödün vermek istemez. Birine bağlanmaktan kaçar. Eğer bu iki tip olumsuz taraflarını törpülerse uyumlu ve uzun süreli bir ilişki olabilir.";
    String basakboga = "Başak ile Boğa arasında uyumlu bir ilişki olması mümkün çünkü her iki tarafın da beklentileri ve davranışları birbiriyle uyum halde. Başak’ın beklentilerini Boğa fazlasıyla karşılar. Güven ve sadakat Boğa için çok önemlidir, Başak da sevdiği zaman sadık ve bağlılığını göstermekten çekinmeyen bir eş olacaktır. Boğa’nın hassas ve duygusal tarafı Başak’ın bazı eleştirel konuşmaları ile rahatsız olsa da, her iki taraf da kişisel zıtlıklar yaşamıyorsa uzun vadede, mutlu bir beraberlik yaşayabilirler.";
    String basakikizler = "Bu iki burcun elementleri birbirinden farklı olsa da ikisi birlikteyken müthiş bir uyum yakalayabilirler. Başak akıllı, sevecen, iyi niyetli, güvenilir ve detaycıdır. ıkizler burcu Başak’ın bu özelliklerinden etkilenir. ıkizler’in zeki, konuşkan, girişken ve hareketli tavrı, her daim içinde bir neşe olması da Başak burcunun ilgisini çeker. Aradığı hayat enerjisini ıkizler’de bulabilir. Ancak ıkizler’ın kıskanç olması ve bunu zaman zaman saklayamaması aralarında soruna neden olabilir. Bu iki burcun birliktelikleri uzun ve eğlenceli olacaktır.";
    String basakyengec = "Başak kadını ile Yengeç erkeği arasında oldukça uyumlu bir ilişki olabilir. Sakin ve evine bağlı olan Yengeç, Başak’ın yönetme isteğine uyum sağlayacak, birbirlerini ihtiyaç duydukları konularda dengeleyeceklerdir. Yengeç’in duygusal ve romantik tarafı Başak’ı etkileyecektir. Yengeç sevdiği zaman sadık ve eğlenceli bir eş olacaktır. Seksüel açıdan her iki taraf da romantik ve arzuludur. Farklılıklar rahatsız edici olmadığı takdirde bu iki burcun anlaşamaması zordur.";
    String basakaslan = "Başak ve Aslan arasında uyumlu bir ilişki olabilir. Başak akıllı, sevecen, iyi niyetli ve detaycıdır. Aslan ise korumacı, sevecen, duygusal ve hareketli bir yapıya sahiptir. Aslan erkeğinin çalışkanlığı, gururlu duruşu Başak kadınını etkiler. Aslan’ın pohpohlanmak ihtiyacını Başak fark eder ve karşılarsa, Aslan’ın onun için yapamayacağı şey kalmaz. Her iki taraf da seksüel yönden oldukça arzuludur. Aralarındaki dengeyi karşılıklı fedakarlıklarla korurlarsa, ömür boyu bir birliktelik yaşayabilirler.";
    String basakbasak = "İki Başak burcu arasında son derece uyumlu bir ilişki yaşanabilir. Karakter özellikleri birbirine çok benzeyeceğinden, ikisinin de rahatlarına çok düşkün olması kimseyi rahatsız etmeyecektir. Yaşadıkları yerlerde titizliğe çok önem verecek, düzen konusunda birbirlerinin alanlarına girmeyeceklerdir. Birbirlerinin zayelse if ve güçlü yanlarını bildiklerinden aralarında çatışma çıkma olasılığı zayelse if olacaktır. Çocuklara olan düşkünlükleri onları birbirine bağlayacak, uzun süreli uyumlu bir ilişki yaşayacaklardır.";
    String basakterazi = "Başak burcu kişilik özellikleriyle, Terazi'yi fazlasıyla etkisi altına alır. Terazi de renkli kişiliği ve samimi tavırlarıyla Başak için eğlenceli bir partnerdir. Terazi ince ruhlu ve anlayışlıdır. ıyi bir sevgilinin yanı sıra iyi bir dinleyici ve iyi bir dost olur. Fakat duygularını kolay kolay belli etmez. Dolayısıyla Başak, Terazi'nin bu gizemli yönünden etkilenir. Başak'ın, Terazi'yi elinde tutabilmesi için duygu ve düşüncelerini yumuşak bir dille else ifade etmesi gerekmektedir. Uzun vadede eğlenceli ve uyumlu bir ilişkileri olabilir.";
    String basakakrep = "Başak burcu ve Akrep burcu ilişkisinde Başak burcunun Akrep burcundan öğreneceği çok şey vardır. Akrep tutkulu, akıllı, duygusal ve açık sözlüdür. Kendine oldukça güvenir. Etkileyici bir dış görünümü vardır. Eğer severse tutkulu bir aşık olacaktır. Başak, Akrep'in cazibesinden fazlasıyla etkilenecektir. Bu ikilinin birliktelikleri hem duygusal, hem tutkulu hem de şehvetli olacaktır. Akrep burcu, Başak burcuna hayal edemeyeceği duyguları yaşatacaktır. Bu da Başak burcunun Akrep burcuna olan bağlılığını arttıracaktır. Başak burcu ilişkide güven arar, aradığı güven ve sadakati ona akrep burcu verecektir.";
    String basakyay = "Bu iki tip birbirini adeta tamamlar ve çok iyi anlaşır. Öncelikle çok iyi iki dost ve ardından da iyi iki sevgili olacaklardır. Güven ve sadakate ihtiyaç duyan Başak'ı, Yay bir nebze de olsa korkutabilir. Fakat kişisel özgürlüklere fazla müdahale edilmezse Yay bunun karşılığını ilişkiye sahip çıkarak verecektir. Seksüel açıdan her iki taraf da romantik ve arzuludur. Başak burcu ve yay burcu ilişkisinde her iki taraf da bağımsız olduğundan birini bile uzun bir ilişkide tutmak oldukça zor olacaktır.";
    String basakoglak = "Başak burcu ve Oğlak burcu çelse ifti uyumlu bir çelse ift olacaktır. Özellikle Başak burcu için Oğlak burcu ideal bir eştir. Her iki burcun da toprak grubunda olması benzer özellikler taşıdıklarını ve birbirlerini anlayacaklarını gösterir. Oğlak erkeğinin gerçekten sevince yapmayacağı fedakarlık yoktur. Her konuda sevdiği kişiye destek olur. Başak burcu eğer başarabilirse oğlak burcunun kalbinde taht kuracaktır. Sevdiğine tamamen bağlı olan Oğlak burcu, Başak burcuna aşkın farklı taraflarını öğretecek ve yaşatacaktır.";
    String basakkova = "Başak burcu ve Kova burcu birbirinden tamamen farklı karakterler olmakla beraber, bu ikilinin birliktelikleri kendisinden tamamen farklı biriyle olma isteğinden doğacaktır. Kova duygusal ve bir o kadar da değişken bir kişiliğe sahiptir. Sürekli bir değişim halindedir. Sürekli değişik ortamlarda, değişik kişilerle olmaktan keyelse if alır. Her iki tipin de ilişkiden ve hayattan beklentileri farklıdır. Kova, Başak burcuna güven vermez. Seksüel açıdan da beklentileri pek birbirlerine uymaz. Uzun vadede uyumlu bir ilişki yakalamaları zordur.";
    String basakbalik = "Başak burcu ile Balık burcu beraberliği oldukça romantik bir beraberlik olacaktır. Bu iki burç karşılıklı olarak birbirlerinin ihtiyaç duydukları özelliklere sahiptirler. Balık burcu Başak burcuna ihtiyaç duyar. Sezgileri oldukça gelişmiş Balık burcu, Başak burcunun içinden gelenleri bilecektir ve ona göre hareket edecektir. Balık burcunun anlayışlı ruhu, Başak burcunu kendisine hayran bırakacaktır. Toprak grubundaki Başak ile su grubundaki Balık anlaşmakta zorlanmadıklarından ilişkileri de oldukça uyumlu olacaktır.";

    // Terazi (Kadın) - X (Erkek)
    String terazikoc = "Terazi kadını duygusal, sevecen ve romantiktir. Karşı cinse nasıl hitap etmesi gerektiğini de iyi bilir. Koç ise neşeli, girişken ve biraz da ayran gönüllüdür. ılişkilerde sadakat problemi yaşar ve kolay kolay kimseye bağlanmaz. Bu iki tip birbirinden tamamen farklıdır. Karşıt burçlar birbirlerini inanılmaz ölçüde çeker ve çekici bulurlar. Koç ve Terazi de böyle bir ikilidir. Birbirlerini dengeleyebildikleri ölçüde, güzel bir beraberlik yaşayabilirler.";
    String teraziboga = "Her iki burç da Venüs tarafından yönetilir. Bu iki karakterin birçok ortak özelliği bulunur. Bu yüzden de aralarında uyumlu bir ilişki olabilir. Boğa erkeği, sert gibi görünse de kısa zamanda sert kabuğunun altında saklı kalan ince ve hassas yapıyı sevdiği kişiye gösterir. Terazi kadını da duygusal, romantik ve zarelse iftir. Birbirlerini iyi anlarlar ve sadakat konusunda bir problem yaşamazlar. Her iki taraf da seksüel yönden arzulu ve duygusaldır.";
    String teraziikizler = "Terazi kadını ve ıkizler erkeği arasında uyumlu bir ilişki olabilir. Her iki burç da hava grubundandır ve değişkendir. ıkizler erkeği Terazi kadınına ihtiyacı olan tüm özgürlüğü verir. Kendisi de gece hayatına ve özgürlüğüne düşkündür. Onunla eğlenceli ve tutkulu bir ilişki yaşayabilirsiniz, ama konu evlilikse iki kez düşünmeli ve sadakat konusunda problem yaşamayacağınızdan emin olmalısınız.";
    String teraziyengec = "Terazi ve Yengeç arasında pek uyumlu bir birlikteliğin olacağı söylenemez. Yengeç erkeği duygusal ve hassastır. Terazi kadını da aynı şekilde romantiktir. Ama iki taraf da duygularını dile getirme konusunda çekingen davranacağından sıkıntılar ortaya çıkabilir. Seksüel açıdan Yengeç oldukça arzulu ve tutkuludur. Aralarında büyük bir aşk varsa ve kişilik olarak zıtlıklar yoksa ilişki yürüyebilir.";
    String teraziaslan = "Aslan erkeği neşeli, girişken ve zekidir. Terazi kadını ise zarelse ifliği ve duygusallığıyla dikkat çeker. Bu ikilinin birlikteliği gayet uyumlu ve uzun süreli olacaktır. Aslan erkeği kişiliği ve cazibesiyle Terazi’yi kolayca etkisi altına alır. ılişkinin ilerleyen zamanlarında Aslan erkeği gururunu ön plana çıkararak baskıcı tavırlar izlemeye başlayabilir ancak Terazi bunu dengelemeyi bilecektir. Her iki taraf da seksüel açıdan tutkuludur ve bu ikilinin ilişkisine “iyi” demek az olur!";
    String terazibasak = "Başak erkeği titiz, tutumlu, düzenlidir. Abartılı titizliği sizi zaman zaman çileden çıkarabilir. O ise Terazi kadının gizemli yapısından etkilenecektir. Terazi ve Başak ikilisinin ilişkisi kolay olmayacaktır ama çok keyelse ifli olabilir. Farklılıklarınızı kabul eder, birbirinizi delicesine eleştirmez ve saygı sınırlarını aşmazsanız, birlikte çok eğlenebilir ve birbirinizden çok şey öğrenebilirsiniz.";
    String teraziterazi = "Terazi kadını ve erkeğinin birçok ortak yönü vardır. ıkisi de yeni yerler keşfedip yeni kişilerle tanışmayı seven dışa dönük ve hareketli kişilerdir. Ayrıca sevecen, samimi, sadık ve duygusal olurlar. Birbirine benzer bu iki kişinin aşka bakış açısı da aynıdır. Ancak bir süre sonra bu benzerlikler ilişkinin monotonlaşıp heyecanını yitirmesine sebep olabilir.";
    String teraziakrep = "Terazi kadını Akrep erkeğini ya çok sever ya da ondan nefret eder. Sahiplenici ve kısıtlayacı tavırları olan Akrep erkeği çok şüphecidir ve bu durum Terazi kadınıyla olan ilişkisini olumsuz etkiler. Terazi kadını sürekli kontrol altında tutulmak istemez. ıki taraf da cinsel açıdan oldukça arzulu ve tutkuludur. Terazi Akrep’in kendisine hükmetmesinden rahatsız olmayıp ipleri onun eline verirse ilişki uyumlu bir şekilde devam edebilir.";
    String teraziyay = "Terazi kadını oldukça duygusal ve romantiktir. Karşı cinse nasıl hitap etmesi gerektiğini iyi bilir. Yay erkeği ise güvenilir, dışa dönük ve neşelidir. ıki burcun ilişkisi gayet uyumlu olabilir. Ancak Yay erkeğinin bağlanmaktan kaçan yapısı, ilişkide uzun vadede sorunlar çıkmasına neden olabilir. Seksüel açıdan iki taraf da romantiktir ve uyumlu bir ilişkileri olur.";
    String terazioglak = "Oğlak erkeği duygusal, düşünceli ve sabırldır. Aşık olduğunda gözü hiçbir şey görmez ve sevdiği kişiyi fazlasıyla sahiplenir. O, duygularını dile getiren ve hislerinden bahseden biri değildir. Çünkü bunu bir zayelse iflık göstergesi olarak kabul eder. Tüm bu özellikleriyle Oğlak erkekleri Terazi kadınlarına pek uygun değildir. Ancak seksüel anlamda birbirlerinin beklentilerini mükemmel şekilde tamamlarlar.";
    String terazikova = "Terazi kadını ve Kova erkeği arasında uyumlu bir ilişki olabilir. ıkiniz de iletişimci olduğunuz için uzun sohbetleriniz, canlı tartışmalarınız olacaktır ve yeni fikirler ortaya çıkacaktır. Ancak Kova burcu erkeği, özgürlüğüne düşkün, yakın ilişkilerden kaçan bir kişidir. Siz ilişkinizin ciddileşmesini beklerken Kova'nın bu konudaki uzaklığı sizi çıldırtabilir. Seksüel yönden iki taraf da aktelse iftir. ıstek ve arzularını kolayca birbirlerine else ifade edebilirler.";
    String terazibalik = "Terazi kadını ve Balık erkeğinin ilişkisi pek de uyumlu olmaz. Balık’ın duygusallığı ve hayal aleminde yaşayan biri olması Terazi için pek cazip değildir. Terazi kadını daha cesur ve ayakları yere sağlam basan erkeklerle daha sağlam bir ilişki kurabilir. Seksüel yönden de Terazi’nin beklentileri karşısında Balık’ın duygusallığı ön plana çıkar. Bu yüzden bu iki burç arasında sorunsuz bir ilişki görmek pek mümkün değildir.";

    // Akrep (Kadın) - X (Erkek)
    String akrepkoc = "Akrep burcu ile Koç burcu uyumunda her iki taraf da bildiğinden şaşmadığı için hep bir üste çıkma çabası içinde olurlar. Akrep burcu; zor güvenen, her zaman kendi isteklerinin olmasını isteyen bir burç olduğundan yine aynı özelliklere sahip Koç burcu ile anlaşmakta biraz zorlanır. Koç zeki, girişken, neşeli ve biraz da ayran gönüllüdür. Özellikle de kısıtlanmaktan ve yönetilmekten hoşlanmaz. Özellikle Koç burcunun ikili ilişkilerde sadakat problemi vardır. Kolay kolay bağlanmaktan hoşlanmaz. Akrep ise kıskançtır ve hükmetmeyi sever. Özetle bu iki burç arasında uyum pek söz konusu değildir.";
    String akrepboga = "Akrep burcu ile Boğa burcu birbiriyle tam bir bütün oluştururlar. Her iki burç da fazla sosyal olmadığından ilişkilerine daha fazla vakit ayırabilirler. Akrep burcu sahiplenici ve kıskançtır, bu noktada Boğa burcu da aynı özellikleri taşıdığından birbirlerini anlarlar. Akrep burcunun öfkelendiği zamanlarda, sabırlı olan Boğa burcu öfkesinin geçmesini bekler ve onu sakinleştirir. Bu da bir ilişki için önemli bir detaydır. Bu ilişki Akrep burcunun öfkesine, Boğa burcunun ise sabrına dayanarak uzun ömürlü bir ilişki olacaktır.";
    String akrepikizler = "Akrep burcunun karanlık ve gizemli tarafları ile ıkizler burcunun aydınlık ve havai taraflarının bir araya gelmesi zor gibi görünse de imkânsız değildir. Akrep burcu ve ıkizler burcu ikilisi hiçbir zaman klasik bir ilişki olmayacaktır. ıkisinin arasındaki en büyük farklardan birisi Akrep burcunun ikizler burcu kadar konuşkan olmamasıdır. Akrep burcu hiç kimseyle konuşmadan günler geçirebilirken, bu ıkizler burcu için neredeyse imkânsızdır. Eğer ıkizler erkeği, Akrep kadınının ona hükmetmesinden rahatsızlık duymaz ve ipleri onun eline verirse mükemmel ve uyumlu bir ilişkileri olabilir.";
    String akrepyengec = "Akrep ve Yengeç arasında uyumlu bir ilişki olabilir. Her iki burç da su grubundandır. Dolayısıyla ortak özellikleri oldukça fazladır. Yengeç hassas, duygusal ve romantiktir. Akrep ise tutkulu, akıllı, duygusal ve açık sözlüdür. Kendine oldukça güvenir. Etkileyici bir dış görünümü vardır. Eğer severse tutkulu bir aşık olacaktır. Yengeç burcu oldukça anlayışlı olduğundan Akrep burcunun öfke nöbetlerinin geçmesini sabırla bekleyecek ve alttan alacaktır. Özellikle Akrep kadınının karşı konulmaz bir seksüel cazibesi vardır. Bu nedenle uyumlu bir ilişki yakalamaları mümkündür.";
    String akrepaslan = "Aslan burcu, yoğun duygularla dolu ve tutkulu bir aşık olan Akrep burcunun büyüsüne çabuk kapılacaktır. Bu iki burcun ilişkilerini zorlayacak en önemli nokta her ikisinin de sabit fikirli olması ve kararlarından vazgeçmemesi olacaktır. Akrep burcu sevdiği zaman tüm benliği ile sevdiğinden bu durum Aslan burcunun çok hoşuna gidecektir. Bir kavga anında her iki taraf da kararlarından vazgeçmediğinden barışmaları kimi zaman zor olacaktır ancak her iki taraf da sevdiğine sadık olduğundan uzun ömürlü bir ilişki olması kuvvetle muhtemeldir.";
    String akrepbasak = "Başak burcu ve Akrep burcunun birlikteliği çok gizemli olabilir. Başak burcu Akrep burcunu çözdüğünü sanır. Ancak onu en çok çözdüğünü sandığı anda akrep burcu onu şaşırtacaktır. Akrep hükmetmeyi sever. Fakat Başak eşitlikçi bir yapıya sahip olduğu için bu durumu kolay kolay kabullenemeyecektir. Anlaşmazlıklar yaşanması muhtemeldir. Seksüel açıdan Akrep fazlasıyla arzulu ve tutkuludur. Fakat Başak için öncelik romantizmdir. Aralarında gerçek bir sevgi varsa uzun süreli bir ilişki olabilir.";
    String akrepterazi = "Bu iki tip doğal cazibelerinden dolayı birbirini fazlasıyla çeker. Akrep kadını tutkulu, duygusal, hareketli ve çekicidir. Terazi ise sevecen, dışa dönük, zarelse if ve duygusaldır. Akrep sahiplenici ve kısıtlayıcıdır. Kıskançlığı ile Terazi'yi çileden çıkarabilir. Terazi burcu yalnız bırakılmaya gelmez. Teselliyi başka kollarda arayacaktır. Bu da sevgilisine ders vermek isteyen akrep burcunun pek de hoşuna gitmeyecektir. Bu nedenle Akrep burcu eğer bir Terazi burcuyla birlikte olmak istiyorsa hayat tarzını biraz değiştirmesi gerekebilir. Yine de birbirlerini seviyorlarsa her şey mümkün. ";
    String akrepakrep = "Akrep'ler arasında uyumlu bir ilişki olabilir. Bu iki tip arasında birçok ortak özellik olduğu gibi bir çok zıtlık da olabilir. Fakat birbirlerini çok iyi anlarlar. Her ikisi de tutkulu, duygusal, hareketli ve çekicidir. Hayatı dolu dolu yaşamaktan keyelse if alırlar. Kıskançlık ve sadakat bu ilişkinin temellerini oluşturur. Akrep burcu karşısındakinin sezgilerini ve içgüdülerini iyi bilir. Bu da karşısındaki Akrep burcunu anlamasını sağlar. Bu ilişkide taraflar konuşmadan sadece gözleriyle dahi anlaşabilir. Akrep burcu sevdiğine tam olarak bağlı olduğundan uzun ömürlü bir ilişki bu çelse iftin kaderi olacaktır.";
    String akrepyay = "Akrep burcu bir bakışta karşısındakinin gözlerini okuyabilen, pratik zekalı ve kurnazdır. Yay burcu ise iyimser ve başkalarının kolayca etkisinde kalabilen bir burçtur. Yay burcu seyahat etmekten çok hoşlanır, buna karşılık akrep burcu da evde oturmaktan büyük keyelse if alır. Yay burcu acımasızca dürüsttür, akrep burcu ise su grubunda olduğundan duygusaldır. Yay burcu karşısındaki Akrep burcundan, sözlerini sakınmayacaktır. Onun bu yorumları da kimi zaman Akrep burcunu yıpratacaktır. Zıt kutupların birbirini çekmemesi halinde birlikteliği pek mümkün değildir.";
    String akrepoglak = "Oğlak disiplinli ve düzenlidir. Bu durumda Akrep ile sorunsuz bir ilişkileri olacaktır. Lüksü ve iyi bir işi seven Oğlak burcu, Akrep burcu ile çok iyi anlaşacaktır. Her iki tip de sadıktır. Her iki taraf da arzulu ve tutkuludur, fakat Akrep burcu özelikle Oğlak burcunun bu tarafını ortaya çıkarması ve onu desteklemesi gerekir. Uzun vadede uyumlu ve güzel bir ilişki yakalamaları mümkündür. Akrep burcu ile Oğlak burcu tensel açıdan da oldukça uyumludur. Bakımına düşkün olan her iki taraf da birbiriyle bir uyum içerisinde olacaktır.";
    String akrepkova = "Akrep burcu duygusaldır ve sezgileriyle hareket eder. Buna karşılık Kova duygusal değildir fazla bağlanmayı sevmez. Akrep burcu ise derin duygular yaşamayı, tutkuyu ve ilgilenilmeyi çok sever. Bu ilişkide tarafları birbirinden ayıran en önemli nokta burası olacaktır. Akrep burcunun duygusallığı daha ağır basar. Kova mantıklıdır. Duygularını fazla belli etmez. Dolayısıyla Akrep duygusal tatminsizlik yaşayabilir. Bu iki tipin uzun vadede uyumlu bir ilişki yakalaması zordur.";
    String akrepbalik = "Her ikisi de su grubuna giren bu beraberlik oldukça güzel bir beraberlik olacaktır. ıki tarafında ruh eşini bulmuş olması kuvvetle muhtemeldir. Akrep burcu birçok burç arasında Balık burcuna kendini çok yakın hissedecektir. Sezgileriyle hareket eden ve karşısındakinin içini gözlerinden okuyan Akrep burcu, Balık burcunun saf ve temiz iç dünyasıyla karşılaşınca çok mutlu olacaktır. Kıskanç Akrep burcunu, Balık burcu sabırla kendisine güvendirmek için uğraşacaktır. Seksüel açıdan duygusal, romantik ve arzuludurlar. Uzun vadede çok uyumlu ve mutlu bir ilişkileri olacaktır.";

    // Yay (Kadın) - X (Erkek)
    String yaykoc = "İki burç da Ateş grubuna dahildir. Zeki, girişken, neşeli ve hareketlidirler. ılişkileri de oldukca renkli olur. ıkisi de benzer özelliklere sahip olduğu için birbirlerini nasıl mutlu edeceklerini bilirler ve anlarlar. Koç erkeği kısıtlayıcı davranmazsa ilişkileri uzun ömürlü olabilir. ıkisinin de en büyük korkusu özgürlüğü ve bireyselliği kaybetmek olduğu için evlilik konusunda yavaş davranırlar.";
    String yayboga = "Bir Boğa erkeğine aşık olduysanız, bu beraberliğin çok kolay olacağını zannetmeyin. Boğa erkeği, Yay kadınının doğasına aykırıdır. Boğa erkeği kısıtlanmaktan hoşlanmaz ve bağımsızlığına aşırı düşkündür. O sizin fazla karamsar olduğunuzu düşünür, siz ise onu sorumsuz bulursunuz. ıki burç arasında uyumlu bir ilişki yakalamak zordur. Kendinizi else ifade ediş tarzlarınız da birbirinden oldukça farklıdır. Ancak karşılıklı saygı ve sevgiyle bu ilişkiyi devam ettirebilirsiniz.";
    String yayikizler = "İkiniz de eğlenceyi ve hareketi sevdiğiniz için aranızda uyumlu bir ilişki olabilir. ıkizler erkeği konuşkan, zeki ve dışa dönüktür. Yay kadını ise güvenilir, neşeli ve ele avuca sığmayan bir karakter çizer. Hayatında heyecan arayan Yay kadını için ıkizler isabetli bir seçimdir. ıkizler erkeğinin parayla arası iyi değildir. Bu yüzden evlilikten sonra çalışma kararı almanız onu mutlu eder. Seksüel açıdan ikiniz de arzulu ve istekli olduğunuz için sorun yaşamazsınız.";
    String yayyengec = "Yengeç erkeği istikrara ve güvene ihtiyaç duyar. Ayrıca hassas, duygusal ve romantiktir. Buna karşılık Yay kadını macera sever ve özgürlüğüne düşkündür. Bu özellikleriniz onda hayranlık uyandırsa da size güvenmesi zaman alacaktır. Bu iki tip birbirini anlamakta ve iletişim kurmakta zorlanabilirler. Ama iki taraf da birbirinin temel özelliklerine saygı gösterirse uzun vadede uyumlu bir ilişki yaşayabilirler.";
    String yayaslan = "Birlikte sekste mükemmel bir uyum yakalayabilirsiniz. Her iki burç da Ateş grubudur ve birbirlerine çok benzerler. Aslan erkeği neşeli, girişken ve romantiktir. Ayrıca lükse düşkünlüğüyle bilinir. Yay kadını da eğlenmeyi, gezmeyi seven neşeli bir karakterdir. Bu iki burç birbirini adeta tamamlar. Önce çok iyi iki dost ardından da sevgili olabilirler. Uzun vadede evliliğe dönüşebilecek uyumlu bir ilişki olacaktır.	";
    String yaybasak = "Başak erkeği, güvenilir, detaycı, akıllı ve sevecendir. Birini sevdiği zaman güvenilir ve sadık bir aşık olur. Yay kadını ise güvenilir, neşeli, zeki ve dışa dönüktür. Bu iki burç önce çok iyi iki dost, ardından da iyi iki sevgili olacaktır. Cinsel yönden de frekansları uyuşur. ıki taraf da romantik ve arzulu olduğu için birlikte uzun vadede eğlenceli ve uyumlu bir aşk yaşayabilirler.";
    String yayterazi = "Bir Terazi erkeğine aşık olduysanız, kendinize çok uygun birini buldunuz demektir. Terazi erkekleri zarelse if, sevecen, duygusal ve romantiktir. Aşkta uyumlu ve sadık bir partner olurlar. Yay kadını ise güvenilir, neşeli ve dışa dönüktür. Bu iki tip birbirlerinden fazlasıyla etkilenir. Eğer birbirlerinin kişilik özelliklerine anlayış gösterirlerse birlikte uyumlu ve eğlenceli bir aşk yaşayabilirler.";
    String yayakrep = "Akrep erkeği gizemli havası ve seksiliği ile sizi kendine çekecektir. Ancak onunla ciddi bir ilişkiye girmeden önce iki kez düşünün. Çünkü seks hayatınız yolunda gitse de iletişim anlamında ciddi problemler yaşayabilirsiniz. Akrep insanı sahiplenen, kıskanç ve hatta takıntılı bir yapıdadır. Siz ise özgürlüğünüze aşırı düşkün olduğunuzdan aranızda sık sık tartışma yaşanabilir.";
    String yayyay = "Yay’lar arasında pek uyumlu bir ilişki söz konusu olmaz. Bu iki insanın benzer özellikleri çok fazla olsa da iki taraf da haşarı birer çocuk mizacındadır. Sosyal yaşamdan ve arkadaşlarıyla vakit geçirmekten çok hoşlandıkları için ilişkiye odaklanamazlar. Bu iki kişi, iyi bir çelse iftten çok iyi birer dost olabilir. şiddetli başlayan bir aşk, hareketli günlerle devam edip kısa süre içinde sona erebilir.";
    String yayoglak = "Yay kadını ve Oğlak erkeği arasında pek uyumlu bir ilişki olmaz. Oğlak'ın iç disiplini ve kuralları arasında kendini kuşatılmış hisseden Yay, her an bu ilişkiden kaçmaya hazırdır. Oğlak erkeğinin tükenmek bilmeyen mantığından sıkılan Yay, kaprisli bir kişiye dönüşebilir. Ancak birbirlerini mutlu etmek için ellerinden geleni yapar ve aynı yönde hareket etmeyi başarırlarsa ömür boyu mutlu olabilirler.";
    String yaykova = "Yay kadını güvenilir, neşeli, zeki ve dışa dönüktür. Bağlanmaktan korkar. Gezmek, eğlenmek ve hayattan zevk almak ister. Kova erkeği ise duygusal ama bir o kadar da değişken bir kişiliğe sahiptir. Birbirlerinin kişilik özelliklerinden etkilenen bu iki burcun insanı seksüel açıdan da birbirine uyum gösterir. Dolayısıyla uzun vadeli ve mükemmel bir ilişkileri olacaktır.";
    String yaybalik = "Özgürlük savunucusu Yay kadını ve duygusal Balık erkeği arasında pek uyumlu bir ilişki olmaz. Bu ilişki sürekli kendi dikenlerine takılacağı için zamanla kısır döngüye girecektir. Yay, bir Balık'ın gereksiz alıngan ve duygusal davranışlarına alışamaz. Üstelik onun kadar içsel düşleri olan biri değildir. Balık ne kadar kendine dönükse, Yay da o kadar çevresiyle barışıktır. Her şeyiyle zor bir ilişkidir ve devam edebilmesi için fazlasıyla çaba gerekir.";

    // Oğlak (Kadın) - X (Erkek)
    String oglakkoc = "Koç erkeği ilk başta sizi cezbedebilir. Ama Oğlak kadını ve Koç erkeğinin ilişkisi oldukça zorlayıcı ve yıpratıcı olur. Çünkü Oğlak, zor ve iddialıdır ve kolay kolay kişiliğinden taviz vermez. Koç erkeği de her zaman kendi yolundan gitmeyi seçer. Bu sebeplerle ikisinin ilişkisinde her iki tarafın da bolca fedakarlık yapması gerekir. Her iki taraf da seksüel açıdan hareketlidir. Aynı yönde hareket etmeyi başarır ve birbirlerine olan saygılarını yitirmezlerse bir ömür boyu mutlu olabilirler.";
    String oglakboga = "Boğa erkeği sorumluluklarının farkında, hırslı ve çalışkandır. Aranızda uyumlu bir ilişki yaşanabilir. Boğa erkeğinin sevdiği kişi için yapmayacağı fedakarlık yoktur. Her konuda sevdiği kişiye destek olur ve evcimendir. Bu özellikleriyle her kayınvalidenin rüyasıdır. Ancak sevdiği kişiyi fazlasıyla sahiplenmesi zaman zaman bunalmanıza sebep olabilir. ıki tarafın ilişkisi seksüel açıdan da tutkulu olacaktır.";
    String oglakikizler = "İkizler erkeğinin huzursuz ve sinirli yapısı Oğlak kadınına hiç uygun değildir. ıkizler erkeği de Oğlak’ın kıskançlığı yüzünden kendini kısıtlanmış hissedip mutsuz olabilir. Eğer aralarındaki aşk tutkulu değilse bu ilişkinin yürümesi oldukça zordur. Oğlak tam bir güven insanıdır ve ilişkide sorumluluklarını fazlasıyla yerine getirir. Aynı ilgiyi göremediğinde ise karşı taraf için hayatı çekilmez hale getirebilir.";
    String oglakyengec = "Yengeç erkeğinin sık sık değişen ruh hali sizi şaşırtsa da onunla birlikte mutlu olabilirsiniz. Yengeç erkeği hassas, duygusal ve fazlasıyla romantiktir. Bir kez sevdi mi yapmayacağı fedakarlık yoktur. Her konuda sevdiği kişiye destek olur. Yalnız utangaç bir mizacı olduğu için kendini else ifade etmekte zorluk çekebilir. ıkiniz de evcimen olduğunuz ve kendinizi güvende hissetmek istediğiniz için birbirinize benzersiniz. Seksüel açıdan iki taraf da arzulu ve tutkuludur. Uzun vadede uyumlu ve güzel bir ilişki yaşayabilirsiniz.";
    String oglakaslan = "Aslan erkeği cömert, aristokrat ruhlu, girişken ve romantiktir. Hayatın güzelliklerine ve lükse çok düşkündür. Oğlak kadını ise sevdiği kişiye her konuda destek olan, duygusal ve düşünceli bir yapıdadır. Sevdiği kişiyi fazla sahiplenir ve kıskanır. Bu ikilinin mutlu olmasının tek koşulu özveridir. Eğer ikiniz de hayat tarzlarınızda değişiklik yapabilir ve birbirinizin isteklerine cevap verebilirseniz uzun vadede uyumlu bir birliktelik yaşayabilirsiniz.";
    String oglakbasak = "Oğlak ve Başak arasında uyumlu bir ilişki yaşanabilir. Başak erkeği iyi niyetli, güvenilir ve detaycıdır. O, sizi meşgul edecek heyecanlı erkeklerden değildir, tersine yanında kendinizi rahat ve mutlu hissetmenizi sağlayacaktır. Bu iki burcun hayattan beklentileri benzer olduğu için kolay anlaşırlar. Hem Başak hem de Oğlak çekingen insanlar oldukları için birbirlerine yaklaşmaları zaman alsa da birbirlerini bulduklarında uzun vadede evliliğe doğru gidecek bir ilişkiye doğru yol alabilirler.";
    String oglakterazi = "Bu iki burcun pek uyumlu bir ilişkisi olmaz. Terazi erkeğinin kararsızlığı Oğlak kadınını yorabilir. Düzeni seven Oğlak ise Terazi’nin hareketli ve değişimden hoşlanan yapısını yadırgayacaktır. Yine de tüm olumsuzluklara rağmen birbirlerine sonsuz güven verebilirler. Benzer faaliyetlerden hoşlanmaları da onları birbirlerine yaklaştırır. Biraz çaba ile uyumlu bir birliktelik yaşayabilirler.";
    String oglakakrep = "Bir Akrep erkeğinin yanına yaklaşmak Oğlak kadını için oldukça tehlikelidir. Akrep burcunda doğan bu adama cinsel organları hükmeder. ıhtiraslı, heyecanlı ve hesaplanamazdır. Onunla yaşayabileceğiniz bir aşk tecrübesi, sizi ihtirasın doruklarına çıkaracaktır. Ancak duygusal olarak yıpranabilirsiniz. ılişkinizde her şeyden çok fiziki çekimin hakimin olmasına razıysanız, çok mutlu olabilirsiniz.";
    String oglakyay = "Yay erkeği neşeli, cazibeli ve manevi yönden zengindir. Size karşılaştığı en ilginç kadın olduğunuz duygusunu verir. O, aslında aşık olmak ister, ama bağlanmaktan çekinir, çünkü bağımsızlığına düşkündür. Onu gerçekten seviyorsanız, sabırlı ve anlayışlı olmalısınız. ılişkiniz ne kadar uzun sürerse birbirinize karşı olan duygularınız o kadar yoğun olacaktır. Her iki taraf da seksüel açıdan hareketlidir ve birbirlerini mutlu etmek için ellerinden geleni yaparlar.";
    String oglakoglak = "Çok çalışkan, üretken, finansal güvenceye önem veren Oğlak ve Oğlak ikilisi, oldukça iyi anlaşır. Hayatları boyunca birlikte yılmadan çalışarak, hedefledikleri yere mutlaka gelen bu ikili, toplum içinde de ideal bir çift olarak tanınır. Belki ilişki çok romantik ve pembe hayallerle süslü olmayabilir, hatta başkalarına sıkıcı bile gelebilir. Ama yine de, karşılıklı olarak geliştirdikleri itimat duygusu, sevgi, bağlılık ve yarttıkları ortak amaçlar uzun süreli beraberliğin en belirgin temel taşlarıdır.Sadakat, ihtiyat ve planlama bu ilişkiyi belirleyen ana başlıklardır. Yaşanabilecek anlaşmazlıkların en büyük kaynağı iki tarafın da ciddi anlamda kararlı ve hatta inatçı olmasıdır. Yine de ortak amaçlara kanalize edilen enerji ve verilen karşılıklı destek, uzun ömürlü bir ilişkinin yaşanmasındaki en önemli etkendir.Bedenselliği ifade eden Toprak elementinden olan Oğlak ikilisi, cinsel yaşamda da oldukça tatminkar bir ilişki yaratırlar. Oğlak’ın en büyük kusuru olan işe odaklanma, diğer Oğlak’ın hayatında hiç olamadığı kadar esnekleşmesine ve eşine tolerans göstermesine nedendir.";
    String oglakkova = "Oğlak kadını ve Kova erkeği arasında pek uyumlu bir ilişki olmaz. Oğlak kadını duygusal ve sabırlıdır. Sevdiği kişiye her konuda destek olur. Kova erkeği isi değişken bir kişiliğe sahiptir. Sürekli farklı ortamlara girmekten ve yeni kişilerle tanışmaktan hoşlanır. Bu halleriyle Oğlak kadınına güven vermesi zaman alır. ıki burç seksüel açıdan da birbirine uymaz.";
    String oglakbalik = "Oğlak ve Balık aşkta mutlu olabilir mi? Kesinlikle evet! Oğlak kadını duygusal, düşünceli ve sevdiği kişiyi destekleyici bir yapıdadır. Bu özellikleriyle Balık erkeğini kolayca kendine aşık edebilir. Balık erkeği de romantizmi ve duygusallığıyla Oğlak’ın ayaklarını yerden kesebilir. ıkisinin arasındaki ilişki kusursuz olacaktır ve yatakta da ilişkileri sorunsuz ilerleyecektir.";

    // Kova (Kadın) - X (Erkek)
    String kovakoc = "Kova burçları elde tutulmayı daha doğrusu sevdikleri tarafından sıkılmayı hiç sevmediklerinden en ufak bir ipleri ele alma durumundan rahatsız olurlar. Koç burcunda da bulunan bu huy, ikili ilişkilerde tarafların birbirlerine müdahale etmelerini engeller. Kova insanı duygusal ve bir o kadar da değişken bir kişiliğe sahiptir. Koç da bir o kadar hareketli, zeki ve girişkendir. Bu iki tip başta birbirlerinden çok etkilenirler. Fakat ileriki zamanlarda Kova'nın duygusallığı ön plana çıkabilir. Koç burcu da bu duygusallığa yeteri kadar cevap veremeyebilir. Bu durumda birbirlerinden uzaklaşabilirler.";
    String kovaboga = "Kova burçları yaşamlarının hiçbir evresinde birine ait olmak, kendini birine mecbur hissetmek istemezler. Hissettikleri zaman bunu davranışlarıyla belli eder, sizden uzaklaşırlar. Boğa burçları ise aşk duygusunu en derine kadar yaşamak isterler, sevdiklerini adeta deli gibi kıskanırlar. Boğa burcunun bu kıskanç tavırları Kova burcu tarafından hiç tahammül edilemeyen bir durum haline gelir. Çok kısa bir süre sonra ilişkide sorunlar tarafları birbirinden uzaklaştırır. Dolayısıyla böyle bir ilişki çok uzun sürmeyecektir.";
    String kovaikizler = "Kova ile ıkizler burcunun uyumu mükemmele yakın olacaktır. Her iki burç da hava gurubundan olduğu için aradaki uyum genelde iyi olur. ıkisi de duygusal ve düşünsel yönden yeniliklere kolayca ayak uydururlar. Her ikisi de özgürlüğüne düşkündür ve kısıtlanmaktan hoşlanmaz. Bu nedenle birbirlerinin bireysel özgürlüklerine saygı duyacaklardır. Kova burcu neşeli, sevecen ve zeki tavırlarıyla çevresindeki insanları kendisine hayran bırakır. ıkizler burcu da kendisine eş olarak zeki ve neşeli birisini arar. Birbirlerini çok iyi tamamlarlar.";
    String kovayengec = "Yengeç burçları oldukça duygusal, hassas ve romantiktirler. Kova burçları ise duygularını dışa vurmayan, olaylara daha çok mantıksal yönüyle bakan insanlardır. Genelde iki taraf arası arkadaşlık dışında duygusal bir ilişki sınırları zorlar. Yengeç burcunun çok akıllı davranması ve Kova kadınına yönlendirildiğini hissettirmemesi gerekir. Her iki taraf da arzulu ve tutkuludur. Fakat Yengeç insanının duygusallığı daha ağır basar. Kova burcu mantıklıdır. Bu durumda Yengeç duygusal tatminsizlik yaşayabilir. Bu iki tipin uzun vadede uyumlu bir ilişki yakalaması zordur.";
    String kovaaslan = "Kendini bulunduğu ortamın lideri olarak gören Aslan burcu genel olarak Kova burcuyla uyumlu bir ilişki gösteremez. ıki tarafın da birlikte olabilmesi için gerekli olan kıvılcımı beklemek kova burcu için eziyet gibidir. Herkesin dikkatini çeken Kova burcu, bir Aslan burcunun dikkatini çekemediğinde sinirlenir, uzun bir süre uğraştıktan sonra da pes eder. Kova burçları ilişkide asla ve asla aldatmaya yönelik bir hareket sergilemezler. Başka birinin varlığını hissettiklerinde bile ilişkiyi kesebilir, yeni bir şans vermeyebilirler. Bu iki burcun ilişkisi tarafların duygusal tavırlarına göre olumlu ya da olumsuz şeklini alacaktır.";
    String kovabasak = "Kova burcunu anlamak diğer burçlarda da olduğu gibi başak burcu için de oldukça zor olacaktır. Başak burcu kova burcunu özgür bıraktığı sürece onu daha yakından tanır, ona göre davranır ise kova da bu ölçüde ona yakınlaşacaktır. Özgürlüğünü hissetmek Kova burcu için çok önemlidir. Karşısında kendisi gibi hareketli, detaycı ama bir o kadar da anlayışlı bir Başak burcu gördüğü takdirde sizi el üstünde tutacaktır. Bu şartlar sağlandığı takdirde Kova burcunun Başak burcu ile hoş bir ilişkiye başlamaması için bir sebep yoktur.";
    String kovaterazi = "Terazi ile Kova birlikteliği her zaman uyum içerisinde olur. Terazi erkeği yanında güvende ve huzurla kalabileceğiniz bir tiptir. Kova kadınının biraz çaba göstermesi gerekebilir. Terazi her zaman sürprizlerden hoşlanır. Bu yüzden iki burcun ilişkisinin sıkıcı ve durağan olması pek mümkün değildir. Ayrıca ikisinin de kıskanç olmaması özgürlüğüne düşkün bu iki burç için de şanstır. Akıllı bir Kova kadını Terazi erkeğini özgür bırakarak, sıkmayacaktır. ıki burç birbirine güven duyuyorsa, uyumlu bir birliktelikleri olur.";
    String kovaakrep = "Akrep burcunun temsil ettiği dominant karakter, Kova burcunda fazlasıyla bulunduğundan bu ilişki zorlu bir ilişki olacaktır. Oldukça kıskanç ve talepkar bir burç olan Akrep burcu, özgürlüğüne düşkün Kova burcu ile pek uyumlu görünmemektedir. Bu beraberlik aşk hayatında iş hayatında göre daha olumlu sonuçlar getirebilir. Arkadaşlık ise ancak ortak ilgi ve bilgi düzeyin bağlı olacaktır. Kova’nın sosyal bir burç olması Akrep burcunu rahatsız etse de Kova burcuna doğru çekilmesine neden olacaktır.";
    String kovayay = "Yay burcu her konuda bilgisi olan, donanımlı, kültürel yönden diğer insanlardan üstün olan bir burçtur. Bu özelliğinin verdiği tutum ile çevrede anlaşılamayan bir karakter olarak görülür. Bu durum Kova burcunun dikkatini çeker. Kova burcu kadını Yay erkeğinden öğrenilecek çok şey olduğunu düşünür ve onu tanımak ister. Bu iki tip birbirlerinden kişilik özellikleri nedeniyle çok etkilenir ve çok iyi dost olurlar. Bu dostluk kısa sürede bir aşka dönüşebilir. Seksüel açıdan birbirlerine uyum gösterirler. Uzun vadede eğlenceli ve mükemmel bir ilişkileri olacaktır.";
    String kovaoglak = "Oğlak burcunun aradığı bağlılığı vermesi oldukça güç olan Kova burcu diğer burçlara nazaran özgürlüğüne oldukça düşkündür. Oğlak burcu ve Kova burcunun en büyük ortak noktaları ise her iki burcun da hırsları ve inatçılığıdır. ıstemediği bir şeyi kimsenin yaptırması mümkün olmayan Oğlak burcunu, kararlılığı ile her şeyi başaracak Kova burcu dize getirecektir. Ancak yine de gelecekleri pek parlak değildir. Her iki tipin de ilişkiden ve hayattan beklentileri farklıdır. Kova burcu, Oğlak'a güven vermez. Seksüel açıdan da beklentileri pek birbirlerine uymaz.";
    String kovakova = "İki tarafın da kova burcu olması, şüphesiz ilişkiyi muhteşem kılacaktır. Zodyak’ın en zeki burcu sayılan Kova burcu, her iki tarafın birbirini her konuda anlayabilecek bir ikili ilişkinin varlığını ortaya koyar. Ancak yine de bağımsızlıklarına düşkünlükleri onların aradıkları güveni birbirlerinde bulamamalarına neden olabilir. Her ikisi de duygusal ve bir o kadar da değişken bir kişiliğe sahiptir. Sürekli bir değişim halindedirler. Seksüel açıdan arzulu olmalarına rağmen birbirlerinden çabuk sıkılmaları olasıdır. Uyumlu ve dengeli bir ilişki kurmak onları zorlayacaktır.";
    String kovabalik = "Balık burcunun romantik ve oldukça duygusal havası başlarda Kova burcunun ilgisini çekecek fakat her zaman olduğu gibi çabuk sıkılacaktır. Balık burcu ise Kova burcunun büyüsüne kapıldıktan sonra onun bu yüzünü gördüğünde kırılacaktır. Uyumlu bir ilişkiye ulaşmak için iki taraf için de fedakarlıklar gerekecektir. Eğer bir Kova burcu, Balık burcuna bağlanmışsa onu asla terk etmez, oldukça sadıktır; sevgisini daimi kılmaya çalışır. Balık burcu da ilişkisinde sadık ve bağlıdır. Alttan alınan her sorun için ilişkinin vadesi uzun olma yolunda ilerleyecektir.";

    // Balık (Kadın) - X (Erkek)
    String balikkoc = "Balık burcu hassas ve narindir. Koç burcu balık burcunun bu özelliklerini çok beğenir. Koç ise içe dönük, aceleci, dikkatsiz ve cüretkardır. Hayatı yüzeysel yaşama taraftarıdır. Balık ise hayatı derinden yaşamak ister. Bu anlamda birbirlerine zıt düşerler. Düşünmeden konuşan Koç erkeğinin sözleri Balık kadınını derinden etkileyip üzer. Koç erkeği sizin duygularınızın altında yatan nedenleri anlamak için çaba göstermez. Koç burcunun maymun iştahı ilişkinin bitmesine neden olabilir. Koç erkeği bu konuda kendisine dikkat etmesi gerekir.";
    String balikboga = "Balık ve Boğa arasında pek uyumlu bir ilişki olmaz. Çünkü bu iki karakter birbirinden çok farklıdır. Boğa mantıklı, sevecen, sağduyulu ve anlayışlıdır. Balık ise romantik, duygusal ve hayalperesttir. Balık burcunun bu denli derin duygusallığını Boğa anlamakta zorluk çeker. Boğa'nın hayata bakış açısı oldukça gerçekçidir. Dolayısıyla Balık'ın bazı konulardaki vurdumduymaz tavırları Boğa'yı çileden çıkarır. Boğa burcunun maddeye fazla önem vermesini de Balık kadını anlayamaz. Bu iki burç birbiriyle yatakta da mükemmel bir birliktelik yakalayamazlar.";
    String balikikizler = "İkizler burcu canlı kıpır kıpır bir yapısı vardır. Girişken, sosyal, eğlenmeyi seven, tiyatroya sinemaya gitmeyi seven bir kişiliğe sahiptir. Evde oturmaktan nefret eder. Balık burcu ise biraz evcimen bir yapısı vardır. ıkizler ile Balık’ın aşması gereken en önemli sorun birisi sosyal iken diğerinin asosyal olmasıdır. Seksüel yönden ise ıkizler burcunun tutkulu yapısı yanında Balık burcunun aşırı duygusallığı ön plana çıkar. Balık erkeğine düşen en önemli görev ıkizler’in duygularını ortaya çıkaracak ortamı hazırlamak olacaktır.";
    String balikyengec = "İki burç da derin duygulara sahiptir. Birbirlerini anlama konusunda hiçbir sıkıntı çekmezler. Yengeç’in aile kurma ve yuva özlemi Balık burcunun çok hoşuna gider. Balık ince düşünür ve sevdiği erkeği mutlu etmek ve şımartmak için elinden gelen çabayı gösterir. Parasal konularda Balık maddiyata önem vermezken Yengeç çok hesaplıdır. Birikim yapmayı seven Yengeç burcu ile geleceğiniz güvence altına almışsınız demektir. Seksüel açıdan duygusal, romantik ve arzuludurlar. Uzun vadede çok uyumlu ve mutlu bir ilişkileri olacaktır.";
    String balikaslan = "Balık ile Aslan burcunun ortak özelliklerinden ilki ikisinin de yaratıcı burçlar olmasıdır. Balık burcunu Aslan burcuna hayran bırakan en temel özellik Aslan burcunun sahip olduğu cömertlik ve ihtişamdır. ıki burç da sanat ve edebiyata çok önem verir. Aslan burcu Balık burcuna cesur olma konusunu öğretirken Balık burcu ise Aslan’a kurnaz olmayı öğretebilir. Aslan'ın hareketli ve dışa dönük kişiliği bu ilişkiyi dengeleyecektir. Uzun vadede uyumlu ve güzel bir ilişki yakalamaları mümkündür.";
    String balikbasak = "Başak burcu Balık burcunun ihtiyacı olan şeyleri belirler bu ihtiyaçları gidermek için canla başla çalışır. Başak, Balık’ın hayal dünyasının genişliğine hayranlık duyar ve onun yanında olmaktan mutluluk duyar. Başak burcu idealist bir burçtur. Kimsenin görmediğini görür. ınce ayrıntılara önem verir. Eleştirici bir kimliği vardır. Balık ise baktığı her şeye güzel gözle baktığı için güzellik görür. Başak burcunun eleştirilerinden incinebilir. Bu anlamda Başak burcu daha dikkatli konuşmaya dikkat etmelidir.";
    String balikterazi = "Balık ile Terazi zarelse if, kibar ve ince yapılı iki burçtur. Balık su elementinin Terazi ise hava elementinin etkisi altındadır. Hava ve su uyumu göz önünde alındığında uyumlu bir çelse ifttir. Terazi burcu kültürel olayları yakından takip eder. Yeni açılan tüm mekanları takip eder. Bu anlamda Balık burcunu çok renkli ve heyecan dolu bir hayat bekliyor. Balık burcu maneviyata daha çok önem verdiği için sosyal rekabetlerin içerisine girmez. Balık ile Terazi yaşantı olarak farklı kutuplarda olsalar da düşünce ve hassaslık konusunda çok iyi anlaşan iki burçtur.";
    String balikakrep = "Akrep burcunun sadık ve tedbirli olması Balık burcunu cezp eder. Akrep erkeği yoğun duygulara sahiptir. Akrep erkeği yalnız kaldığı zaman mutlaka onunla iletişime geçilmelidir. Balık hassas duyguları ve gelişmiş önsezileri ile nerede ne yapması gerektiğini iyi bilir. Balık kadınının kendinden emin tavır sergilemesi Akrep erkeğinin hoşuna gider. Onu bu mücadelesinde sonuna kadar destekler. Balık burcu ile Akrep burcu arasındaki cinsel uyum da mükemmeldir. Balık insanı için seks gerçek aşkın en kutsal biçimde else ifade edilmesidir. Balık burcunun bu görüşü Akrep’in oldukça hoşuna gider.";
    String balikyay = "Yay burcu açık sözlü, Balık burcu kırılgan olduğu için bu iki burcun uyumu çok zordur. Yay kimi zaman patavatsız olduğu ve ağzından çıkanı kulağı duymadığı için sık sık Balık burcunu kırabilir. Diğer yandan Balık burcunun hayata bakış şekli Yay'ı eğlendirebilir. Fakat tavırları uzun vadede Yay burcunu çileden çıkaracaktır. Bu iki tip arasında seksüel açıdan çok da uyumlu bir ilişki olmaz. Balık burcunun duygusal yönü ağır basacağından Yay kadar aktelse if olmayacaktır. Ancak Yay, Balık burcunun iç dünyasına girer ve onu anlamak için çaba sarf ederse ilişkileri devam edebilir.";
    String balikoglak = "Balık duygusal, düşünceli ve sabırlıdır. Bir kez sevdi mi yapamayacağı fedakarlık yoktur. Her konuda sevdiği kişiye destek olur. Yalnız utangaç bir mizacı olduğu için kendini else ifade etmekte zorluk çekebilir. Sevdiği kişiyi fazlasıyla sahiplenir ve kıskançtır. Tam bir güven insanıdır. Fakat Oğlak burcunu anlamak onun için zor olabilir. Çünkü Oğlak duygularını belli etmez ve incinmekten korkar. Oğlak burcunun hedefleri büyüktür. Balık burcu bu hedeflerin sevgilerinin önüne geçmesinden endişe duyar. Oğlak burcunun maddeci yapısı duygusal Balık burcunda korkular oluşmasına neden olur.";
    String balikkova = "Kova burcu ilgisiz ve gizemli havası ile Balık burcunun canını fena halde sıkabilir. Kova erkeği sabit fikirli, inatçı bir kişiliğe sahiptir. Balık burcunun ise değişken bir yapısı vardır. ılişkinin yürümesi için Balık burcunun birçok taviz vermesi gerekir. Kova burcu sosyal olarak güçlü bir kişiliğe sahip gibi görünse de tekilde aslında zaafları olduğunu görürsünüz. Bu da Balık kadınını olumsuz etkileyebilir. Bütün zıtlıkları üzerinde barındıran Balık burcu ile Kova burcunun anlaşması oldukça zordur.";
    String balikbalik = "Balık erkeği ile Balık kadınının uyumu mükemmeldir! ıki burç da maddiyata önem vermediği için en büyük sıkıntıları maddiyattan yana olur. Balık burcu yalnızlığı sevdiği kadar birlikte hareket etmeyi de çok sever. Bu yüzden partilerde, açılışlarda, eğlencelerde bulunmak için can atarlar. Balık kadını ile Balık erkeğinin cinsel uyumu da tıpkı duygusal ilişkileri gibi mükemmeldir. ıçgüdüleri kuvvetli olan Balık karşısındaki insanın ne istediğini bilir ve karşısındakini mutlu etmek için elinden gelen her şeyi yapar.";

    ImageView imagebutton;
    int burckadin, burcerkek;
    Tracker t;
    int color = Color.parseColor("#000000");
    int color2 = Color.parseColor("#313131");
    Window window;
    ActionBar bar;
    Spinner spinner, spinner2;
    Intent intent;

    String[] strings = {"21 Mart–19 Nisan", "20 Nisan–20 Mayıs", "21 Mayıs–21 Haziran", "22 Haziran–22 Temmuz",
            "23 Temmuz–22 Ağustos", "23 Ağustos–22 Eylül", "23 Eylül–22 Ekim", "23 Ekim–21 Kasım", "23 Kasım–21 Aralık",
            "22 Aralık–19 Ocak", "20 Ocak–18 Şubat", "19 Şubat–20 Mart"};
    int images[] = {R.drawable.burc_koc, R.drawable.burc_boga, R.drawable.burc_ikizler, R.drawable.burc_yengec,
            R.drawable.burc_aslan, R.drawable.burc_basak, R.drawable.burc_terazi, R.drawable.burc_akrep,
            R.drawable.burc_yay, R.drawable.burc_oglak, R.drawable.burc_kova, R.drawable.burc_balik};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_second, container, false);

        // Premium & AdMob
        boolean premium = MainActivity.premium;
        AdView adView = (AdView) rootView.findViewById(R.id.adMob);
        if (premium) {
            adView.setVisibility(View.GONE);
        } else {
            AdRequest adRequest = new AdRequest.Builder().addTestDevice("0A83AF9337EAE655A7B29C5B61372D84").build();
            adView.loadAd(adRequest);
        }

        // Colored bars
        if (android.os.Build.VERSION.SDK_INT >= 21) {
            window = getActivity().getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(color);

            bar = ((MainActivity) getActivity()).getSupportActionBar();
            bar.setBackgroundDrawable(new ColorDrawable(color2));
        } else {
            bar = ((MainActivity) getActivity()).getSupportActionBar();
            bar.setBackgroundDrawable(new ColorDrawable(color2));
        }

        // Analytics
        t = ((AnalyticsApplication) getActivity().getApplication()).getDefaultTracker();
        t.setScreenName("Burç uyumu");
        t.enableAdvertisingIdCollection(true);
        t.send(new HitBuilders.ScreenViewBuilder().build());

        SpinnerAdapter spinneradapter = new SpinnerAdapter(getActivity(), R.layout.spinner_row, strings);

        spinner = (Spinner) rootView.findViewById(R.id.spinner1);
        spinner.setAdapter(spinneradapter);

        spinner2 = (Spinner) rootView.findViewById(R.id.spinner2);
        spinner2.setAdapter(spinneradapter);

        spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapter, View v, int position, long lng) {
                if (position == 0) {
                    burckadin = 0;
                } else if (position == 1) {
                    burckadin = 1;
                } else if (position == 2) {
                    burckadin = 2;
                } else if (position == 3) {
                    burckadin = 3;
                } else if (position == 4) {
                    burckadin = 4;
                } else if (position == 5) {
                    burckadin = 5;
                } else if (position == 6) {
                    burckadin = 6;
                } else if (position == 7) {
                    burckadin = 7;
                } else if (position == 8) {
                    burckadin = 8;
                } else if (position == 9) {
                    burckadin = 9;
                } else if (position == 10) {
                    burckadin = 10;
                } else {
                    burckadin = 11;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });

        spinner2.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapter, View v, int position, long lng) {

                if (position == 0) {
                    burcerkek = 0;
                } else if (position == 1) {
                    burcerkek = 1;
                } else if (position == 2) {
                    burcerkek = 2;
                } else if (position == 3) {
                    burcerkek = 3;
                } else if (position == 4) {
                    burcerkek = 4;
                } else if (position == 5) {
                    burcerkek = 5;
                } else if (position == 6) {
                    burcerkek = 6;
                } else if (position == 7) {
                    burcerkek = 7;
                } else if (position == 8) {
                    burcerkek = 8;
                } else if (position == 9) {
                    burcerkek = 9;
                } else if (position == 10) {
                    burcerkek = 10;
                } else {
                    burcerkek = 11;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }

        });

        // burç uyumu texti
        imagebutton = (ImageView) rootView.findViewById(R.id.imageView1);
        imagebutton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getActivity(), BurcUyumuActivity.class);
                intent.putExtra("burckadin", burckadin);
                intent.putExtra("burcerkek", burcerkek);

                // Koç (Kadin) - X (Erkek)
                if (burckadin == 0 && burcerkek == 0) {
                    intent.putExtra("burcuyumu", kockoc);
                    intent.putExtra("number", 90);
                } else if (burckadin == 0 && burcerkek == 1) {
                    intent.putExtra("burcuyumu", kocboga);
                    intent.putExtra("number", 40);
                } else if (burckadin == 0 && burcerkek == 2) {
                    intent.putExtra("burcuyumu", kocikizler);
                    intent.putExtra("number", 90);
                } else if (burckadin == 0 && burcerkek == 3) {
                    intent.putExtra("burcuyumu", kocyengec);
                    intent.putExtra("number", 70);
                } else if (burckadin == 0 && burcerkek == 4) {
                    intent.putExtra("burcuyumu", kocaslan);
                    intent.putExtra("number", 80);
                } else if (burckadin == 0 && burcerkek == 5) {
                    intent.putExtra("burcuyumu", kocbasak);
                    intent.putExtra("number", 30);
                } else if (burckadin == 0 && burcerkek == 6) {
                    intent.putExtra("burcuyumu", kocterazi);
                    intent.putExtra("number", 60);
                } else if (burckadin == 0 && burcerkek == 7) {
                    intent.putExtra("burcuyumu", kocakrep);
                    intent.putExtra("number", 80);
                } else if (burckadin == 0 && burcerkek == 8) {
                    intent.putExtra("burcuyumu", kocyay);
                    intent.putExtra("number", 100);
                } else if (burckadin == 0 && burcerkek == 9) {
                    intent.putExtra("burcuyumu", kocoglak);
                    intent.putExtra("number", 40);
                } else if (burckadin == 0 && burcerkek == 10) {
                    intent.putExtra("burcuyumu", kockova);
                    intent.putExtra("number", 60);
                } else if (burckadin == 0 && burcerkek == 11) {
                    intent.putExtra("burcuyumu", kocbalik);
                    intent.putExtra("number", 30);
                }

                // Boga (Kadin) - X (Erkek)
                else if (burckadin == 1 && burcerkek == 0) {
                    intent.putExtra("burcuyumu", bogakoc);
                    intent.putExtra("number", 70);
                } else if (burckadin == 1 && burcerkek == 1) {
                    intent.putExtra("burcuyumu", bogaboga);
                    intent.putExtra("number", 90);
                } else if (burckadin == 1 && burcerkek == 2) {
                    intent.putExtra("burcuyumu", bogaikizler);
                    intent.putExtra("number", 90);
                } else if (burckadin == 1 && burcerkek == 3) {
                    intent.putExtra("burcuyumu", bogayengec);
                    intent.putExtra("number", 90);
                } else if (burckadin == 1 && burcerkek == 4) {
                    intent.putExtra("burcuyumu", bogaaslan);
                    intent.putExtra("number", 80);
                } else if (burckadin == 1 && burcerkek == 5) {
                    intent.putExtra("burcuyumu", bogabasak);
                    intent.putExtra("number", 60);
                } else if (burckadin == 1 && burcerkek == 6) {
                    intent.putExtra("burcuyumu", bogaterazi);
                    intent.putExtra("number", 80);
                } else if (burckadin == 1 && burcerkek == 7) {
                    intent.putExtra("burcuyumu", bogaakrep);
                    intent.putExtra("number", 80);
                } else if (burckadin == 1 && burcerkek == 8) {
                    intent.putExtra("burcuyumu", bogayay);
                    intent.putExtra("number", 20);
                } else if (burckadin == 1 && burcerkek == 9) {
                    intent.putExtra("burcuyumu", bogaoglak);
                    intent.putExtra("number", 90);
                } else if (burckadin == 1 && burcerkek == 10) {
                    intent.putExtra("burcuyumu", bogakova);
                    intent.putExtra("number", 90);
                } else if (burckadin == 1 && burcerkek == 11) {
                    intent.putExtra("burcuyumu", bogabalik);
                    intent.putExtra("number", 90);
                }

                // ikizler (Kadin) - X (Erkek)

                else if (burckadin == 2 && burcerkek == 0) {
                    intent.putExtra("burcuyumu", ikizlerkoc);
                    intent.putExtra("number", 70);
                } else if (burckadin == 2 && burcerkek == 1) {
                    intent.putExtra("burcuyumu", ikizlerboga);
                    intent.putExtra("number", 70);
                } else if (burckadin == 2 && burcerkek == 2) {
                    intent.putExtra("burcuyumu", ikizlerikizler);
                    intent.putExtra("number", 80);
                } else if (burckadin == 2 && burcerkek == 3) {
                    intent.putExtra("burcuyumu", ikizleryengec);
                    intent.putExtra("number", 50);
                } else if (burckadin == 2 && burcerkek == 4) {
                    intent.putExtra("burcuyumu", ikizleraslan);
                    intent.putExtra("number", 80);
                } else if (burckadin == 2 && burcerkek == 5) {
                    intent.putExtra("burcuyumu", ikizlerbasak);
                    intent.putExtra("number", 40);
                } else if (burckadin == 2 && burcerkek == 6) {
                    intent.putExtra("burcuyumu", ikizlerterazi);
                    intent.putExtra("number", 90);
                } else if (burckadin == 2 && burcerkek == 7) {
                    intent.putExtra("burcuyumu", ikizlerakrep);
                    intent.putExtra("number", 40);
                } else if (burckadin == 2 && burcerkek == 8) {
                    intent.putExtra("burcuyumu", ikizleryay);
                    intent.putExtra("number", 80);
                } else if (burckadin == 2 && burcerkek == 9) {
                    intent.putExtra("burcuyumu", ikizleroglak);
                    intent.putExtra("number", 40);
                } else if (burckadin == 2 && burcerkek == 10) {
                    intent.putExtra("burcuyumu", ikizlerkova);
                    intent.putExtra("number", 100);
                } else if (burckadin == 2 && burcerkek == 11) {
                    intent.putExtra("burcuyumu", ikizlerbalik);
                    intent.putExtra("number", 20);
                }

                // Yengeç (Kadin) - X (Erkek)

                else if (burckadin == 3 && burcerkek == 0) {
                    intent.putExtra("burcuyumu", yengeckoc);
                    intent.putExtra("number", 50);
                } else if (burckadin == 3 && burcerkek == 1) {
                    intent.putExtra("burcuyumu", yengecboga);
                    intent.putExtra("number", 80);
                } else if (burckadin == 3 && burcerkek == 2) {
                    intent.putExtra("burcuyumu", yengecikizler);
                    intent.putExtra("number", 50);
                } else if (burckadin == 3 && burcerkek == 3) {
                    intent.putExtra("burcuyumu", yengecyengec);
                    intent.putExtra("number", 80);
                } else if (burckadin == 3 && burcerkek == 4) {
                    intent.putExtra("burcuyumu", yengecaslan);
                    intent.putExtra("number", 60);
                } else if (burckadin == 3 && burcerkek == 5) {
                    intent.putExtra("burcuyumu", yengecbasak);
                    intent.putExtra("number", 40);
                } else if (burckadin == 3 && burcerkek == 6) {
                    intent.putExtra("burcuyumu", yengecterazi);
                    intent.putExtra("number", 70);
                } else if (burckadin == 3 && burcerkek == 7) {
                    intent.putExtra("burcuyumu", yengecakrep);
                    intent.putExtra("number", 90);
                } else if (burckadin == 3 && burcerkek == 8) {
                    intent.putExtra("burcuyumu", yengecyay);
                    intent.putExtra("number", 20);
                } else if (burckadin == 3 && burcerkek == 9) {
                    intent.putExtra("burcuyumu", yengecoglak);
                    intent.putExtra("number", 70);
                } else if (burckadin == 3 && burcerkek == 10) {
                    intent.putExtra("burcuyumu", yengeckova);
                    intent.putExtra("number", 40);
                } else if (burckadin == 3 && burcerkek == 11) {
                    intent.putExtra("burcuyumu", yengecbalik);
                    intent.putExtra("number", 100);
                }

                // Aslan (Kadin) - X (Erkek)

                else if (burckadin == 4 && burcerkek == 0) {
                    intent.putExtra("burcuyumu", aslankoc);
                    intent.putExtra("number", 70);
                } else if (burckadin == 4 && burcerkek == 1) {
                    intent.putExtra("burcuyumu", aslanboga);
                    intent.putExtra("number", 70);
                } else if (burckadin == 4 && burcerkek == 2) {
                    intent.putExtra("burcuyumu", aslanikizler);
                    intent.putExtra("number", 90);
                } else if (burckadin == 4 && burcerkek == 3) {
                    intent.putExtra("burcuyumu", aslanyengec);
                    intent.putExtra("number", 60);
                } else if (burckadin == 4 && burcerkek == 4) {
                    intent.putExtra("burcuyumu", aslanaslan);
                    intent.putExtra("number", 70);
                } else if (burckadin == 4 && burcerkek == 5) {
                    intent.putExtra("burcuyumu", aslanbasak);
                    intent.putExtra("number", 60);
                } else if (burckadin == 4 && burcerkek == 6) {
                    intent.putExtra("burcuyumu", aslanterazi);
                    intent.putExtra("number", 90);
                } else if (burckadin == 4 && burcerkek == 7) {
                    intent.putExtra("burcuyumu", aslanakrep);
                    intent.putExtra("number", 80);
                } else if (burckadin == 4 && burcerkek == 8) {
                    intent.putExtra("burcuyumu", aslanyay);
                    intent.putExtra("number", 90);
                } else if (burckadin == 4 && burcerkek == 9) {
                    intent.putExtra("burcuyumu", aslanoglak);
                    intent.putExtra("number", 40);
                } else if (burckadin == 4 && burcerkek == 10) {
                    intent.putExtra("burcuyumu", aslankova);
                    intent.putExtra("number", 70);
                } else if (burckadin == 4 && burcerkek == 11) {
                    intent.putExtra("burcuyumu", aslanbalik);
                    intent.putExtra("number", 30);
                }

                // Basak (Kadin) - X (Erkek)

                else if (burckadin == 5 && burcerkek == 0) {
                    intent.putExtra("burcuyumu", basakkoc);
                    intent.putExtra("number", 70);
                } else if (burckadin == 5 && burcerkek == 1) {
                    intent.putExtra("burcuyumu", basakboga);
                    intent.putExtra("number", 80);
                } else if (burckadin == 5 && burcerkek == 2) {
                    intent.putExtra("burcuyumu", basakikizler);
                    intent.putExtra("number", 80);
                } else if (burckadin == 5 && burcerkek == 3) {
                    intent.putExtra("burcuyumu", basakyengec);
                    intent.putExtra("number", 70);
                } else if (burckadin == 5 && burcerkek == 4) {
                    intent.putExtra("burcuyumu", basakaslan);
                    intent.putExtra("number", 90);
                } else if (burckadin == 5 && burcerkek == 5) {
                    intent.putExtra("burcuyumu", basakbasak);
                    intent.putExtra("number", 90);
                } else if (burckadin == 5 && burcerkek == 6) {
                    intent.putExtra("burcuyumu", basakterazi);
                    intent.putExtra("number", 80);
                } else if (burckadin == 5 && burcerkek == 7) {
                    intent.putExtra("burcuyumu", basakakrep);
                    intent.putExtra("number", 80);
                } else if (burckadin == 5 && burcerkek == 8) {
                    intent.putExtra("burcuyumu", basakyay);
                    intent.putExtra("number", 90);
                } else if (burckadin == 5 && burcerkek == 9) {
                    intent.putExtra("burcuyumu", basakoglak);
                    intent.putExtra("number", 100);
                } else if (burckadin == 5 && burcerkek == 10) {
                    intent.putExtra("burcuyumu", basakkova);
                    intent.putExtra("number", 30);
                } else if (burckadin == 5 && burcerkek == 11) {
                    intent.putExtra("burcuyumu", basakbalik);
                    intent.putExtra("number", 100);
                }

                // Terazi (Kadin) - X (Erkek)

                else if (burckadin == 6 && burcerkek == 0) {
                    intent.putExtra("burcuyumu", terazikoc);
                    intent.putExtra("number", 60);
                } else if (burckadin == 6 && burcerkek == 1) {
                    intent.putExtra("burcuyumu", teraziboga);
                    intent.putExtra("number", 90);
                } else if (burckadin == 6 && burcerkek == 2) {
                    intent.putExtra("burcuyumu", teraziikizler);
                    intent.putExtra("number", 80);
                } else if (burckadin == 6 && burcerkek == 3) {
                    intent.putExtra("burcuyumu", teraziyengec);
                    intent.putExtra("number", 70);
                } else if (burckadin == 6 && burcerkek == 4) {
                    intent.putExtra("burcuyumu", teraziaslan);
                    intent.putExtra("number", 100);
                } else if (burckadin == 6 && burcerkek == 5) {
                    intent.putExtra("burcuyumu", terazibasak);
                    intent.putExtra("number", 80);
                } else if (burckadin == 6 && burcerkek == 6) {
                    intent.putExtra("burcuyumu", teraziterazi);
                    intent.putExtra("number", 70);
                } else if (burckadin == 6 && burcerkek == 7) {
                    intent.putExtra("burcuyumu", teraziakrep);
                    intent.putExtra("number", 50);
                } else if (burckadin == 6 && burcerkek == 8) {
                    intent.putExtra("burcuyumu", teraziyay);
                    intent.putExtra("number", 60);
                } else if (burckadin == 6 && burcerkek == 9) {
                    intent.putExtra("burcuyumu", terazioglak);
                    intent.putExtra("number", 80);
                } else if (burckadin == 6 && burcerkek == 10) {
                    intent.putExtra("burcuyumu", terazikova);
                    intent.putExtra("number", 90);
                } else if (burckadin == 6 && burcerkek == 11) {
                    intent.putExtra("burcuyumu", terazibalik);
                    intent.putExtra("number", 10);
                }

                // Akrep (Kadin) - X (Erkek)

                else if (burckadin == 7 && burcerkek == 0) {
                    intent.putExtra("burcuyumu", akrepkoc);
                    intent.putExtra("number", 40);
                } else if (burckadin == 7 && burcerkek == 1) {
                    intent.putExtra("burcuyumu", akrepboga);
                    intent.putExtra("number", 80);
                } else if (burckadin == 7 && burcerkek == 2) {
                    intent.putExtra("burcuyumu", akrepikizler);
                    intent.putExtra("number", 70);
                } else if (burckadin == 7 && burcerkek == 3) {
                    intent.putExtra("burcuyumu", akrepyengec);
                    intent.putExtra("number", 90);
                } else if (burckadin == 7 && burcerkek == 4) {
                    intent.putExtra("burcuyumu", akrepaslan);
                    intent.putExtra("number", 80);
                } else if (burckadin == 7 && burcerkek == 5) {
                    intent.putExtra("burcuyumu", akrepbasak);
                    intent.putExtra("number", 70);
                } else if (burckadin == 7 && burcerkek == 6) {
                    intent.putExtra("burcuyumu", akrepterazi);
                    intent.putExtra("number", 50);
                } else if (burckadin == 7 && burcerkek == 7) {
                    intent.putExtra("burcuyumu", akrepakrep);
                    intent.putExtra("number", 80);
                } else if (burckadin == 7 && burcerkek == 8) {
                    intent.putExtra("burcuyumu", akrepyay);
                    intent.putExtra("number", 40);
                } else if (burckadin == 7 && burcerkek == 9) {
                    intent.putExtra("burcuyumu", akrepoglak);
                    intent.putExtra("number", 90);
                } else if (burckadin == 7 && burcerkek == 10) {
                    intent.putExtra("burcuyumu", akrepkova);
                    intent.putExtra("number", 40);
                } else if (burckadin == 7 && burcerkek == 11) {
                    intent.putExtra("burcuyumu", akrepbalik);
                    intent.putExtra("number", 90);
                }

                // Yay (Kadin) - X (Erkek)

                else if (burckadin == 8 && burcerkek == 0) {
                    intent.putExtra("burcuyumu", yaykoc);
                    intent.putExtra("number", 50);
                } else if (burckadin == 8 && burcerkek == 1) {
                    intent.putExtra("burcuyumu", yayboga);
                    intent.putExtra("number", 40);
                } else if (burckadin == 8 && burcerkek == 2) {
                    intent.putExtra("burcuyumu", yayikizler);
                    intent.putExtra("number", 70);
                } else if (burckadin == 8 && burcerkek == 3) {
                    intent.putExtra("burcuyumu", yayyengec);
                    intent.putExtra("number", 50);
                } else if (burckadin == 8 && burcerkek == 4) {
                    intent.putExtra("burcuyumu", yayaslan);
                    intent.putExtra("number", 90);
                } else if (burckadin == 8 && burcerkek == 5) {
                    intent.putExtra("burcuyumu", yaybasak);
                    intent.putExtra("number", 80);
                } else if (burckadin == 8 && burcerkek == 6) {
                    intent.putExtra("burcuyumu", yayterazi);
                    intent.putExtra("number", 100);
                } else if (burckadin == 8 && burcerkek == 7) {
                    intent.putExtra("burcuyumu", yayakrep);
                    intent.putExtra("number", 60);
                } else if (burckadin == 8 && burcerkek == 8) {
                    intent.putExtra("burcuyumu", yayyay);
                    intent.putExtra("number", 40);
                } else if (burckadin == 8 && burcerkek == 9) {
                    intent.putExtra("burcuyumu", yayoglak);
                    intent.putExtra("number", 70);
                } else if (burckadin == 8 && burcerkek == 10) {
                    intent.putExtra("burcuyumu", yaykova);
                    intent.putExtra("number", 80);
                } else if (burckadin == 8 && burcerkek == 11) {
                    intent.putExtra("burcuyumu", yaybalik);
                    intent.putExtra("number", 30);
                }

                // Oglak (Kadin) - X (Erkek)

                else if (burckadin == 9 && burcerkek == 0) {
                    intent.putExtra("burcuyumu", oglakkoc);
                    intent.putExtra("number", 40);
                } else if (burckadin == 9 && burcerkek == 1) {
                    intent.putExtra("burcuyumu", oglakboga);
                    intent.putExtra("number", 80);
                } else if (burckadin == 9 && burcerkek == 2) {
                    intent.putExtra("burcuyumu", oglakikizler);
                    intent.putExtra("number", 50);
                } else if (burckadin == 9 && burcerkek == 3) {
                    intent.putExtra("burcuyumu", oglakyengec);
                    intent.putExtra("number", 90);
                } else if (burckadin == 9 && burcerkek == 4) {
                    intent.putExtra("burcuyumu", oglakaslan);
                    intent.putExtra("number", 60);
                } else if (burckadin == 9 && burcerkek == 5) {
                    intent.putExtra("burcuyumu", oglakbasak);
                    intent.putExtra("number", 80);
                } else if (burckadin == 9 && burcerkek == 6) {
                    intent.putExtra("burcuyumu", oglakterazi);
                    intent.putExtra("number", 80);
                } else if (burckadin == 9 && burcerkek == 7) {
                    intent.putExtra("burcuyumu", oglakakrep);
                    intent.putExtra("number", 60);
                } else if (burckadin == 9 && burcerkek == 8) {
                    intent.putExtra("burcuyumu", oglakyay);
                    intent.putExtra("number", 80);
                } else if (burckadin == 9 && burcerkek == 9) {
                    intent.putExtra("burcuyumu", oglakoglak);
                    intent.putExtra("number", 90);
                } else if (burckadin == 9 && burcerkek == 10) {
                    intent.putExtra("burcuyumu", oglakkova);
                    intent.putExtra("number", 50);
                } else if (burckadin == 9 && burcerkek == 11) {
                    intent.putExtra("burcuyumu", oglakbalik);
                    intent.putExtra("number", 90);
                }

                // Kova (Kadin) - X (Erkek)

                else if (burckadin == 10 && burcerkek == 0) {
                    intent.putExtra("burcuyumu", kovakoc);
                    intent.putExtra("number", 60);
                } else if (burckadin == 10 && burcerkek == 1) {
                    intent.putExtra("burcuyumu", kovaboga);
                    intent.putExtra("number", 20);
                } else if (burckadin == 10 && burcerkek == 2) {
                    intent.putExtra("burcuyumu", kovaikizler);
                    intent.putExtra("number", 100);
                } else if (burckadin == 10 && burcerkek == 3) {
                    intent.putExtra("burcuyumu", kovayengec);
                    intent.putExtra("number", 40);
                } else if (burckadin == 10 && burcerkek == 4) {
                    intent.putExtra("burcuyumu", kovaaslan);
                    intent.putExtra("number", 50);
                } else if (burckadin == 10 && burcerkek == 5) {
                    intent.putExtra("burcuyumu", kovabasak);
                    intent.putExtra("number", 70);
                } else if (burckadin == 10 && burcerkek == 6) {
                    intent.putExtra("burcuyumu", kovaterazi);
                    intent.putExtra("number", 70);
                } else if (burckadin == 10 && burcerkek == 7) {
                    intent.putExtra("burcuyumu", kovaakrep);
                    intent.putExtra("number", 30);
                } else if (burckadin == 10 && burcerkek == 8) {
                    intent.putExtra("burcuyumu", kovayay);
                    intent.putExtra("number", 90);
                } else if (burckadin == 10 && burcerkek == 9) {
                    intent.putExtra("burcuyumu", kovaoglak);
                    intent.putExtra("number", 40);
                } else if (burckadin == 10 && burcerkek == 10) {
                    intent.putExtra("burcuyumu", kovakova);
                    intent.putExtra("number", 50);
                } else if (burckadin == 10 && burcerkek == 11) {
                    intent.putExtra("burcuyumu", kovabalik);
                    intent.putExtra("number", 80);
                }

                // Balik (Kadin) - X (Erkek)

                else if (burckadin == 11 && burcerkek == 0) {
                    intent.putExtra("burcuyumu", balikkoc);
                    intent.putExtra("number", 70);
                } else if (burckadin == 11 && burcerkek == 1) {
                    intent.putExtra("burcuyumu", balikboga);
                    intent.putExtra("number", 30);
                } else if (burckadin == 11 && burcerkek == 2) {
                    intent.putExtra("burcuyumu", balikikizler);
                    intent.putExtra("number", 40);
                } else if (burckadin == 11 && burcerkek == 3) {
                    intent.putExtra("burcuyumu", balikyengec);
                    intent.putExtra("number", 100);
                } else if (burckadin == 11 && burcerkek == 4) {
                    intent.putExtra("burcuyumu", balikaslan);
                    intent.putExtra("number", 80);
                } else if (burckadin == 11 && burcerkek == 5) {
                    intent.putExtra("burcuyumu", balikbasak);
                    intent.putExtra("number", 40);
                } else if (burckadin == 11 && burcerkek == 6) {
                    intent.putExtra("burcuyumu", balikterazi);
                    intent.putExtra("number", 70);
                } else if (burckadin == 11 && burcerkek == 7) {
                    intent.putExtra("burcuyumu", balikakrep);
                    intent.putExtra("number", 80);
                } else if (burckadin == 11 && burcerkek == 8) {
                    intent.putExtra("burcuyumu", balikyay);
                    intent.putExtra("number", 50);
                } else if (burckadin == 11 && burcerkek == 9) {
                    intent.putExtra("burcuyumu", balikoglak);
                    intent.putExtra("number", 40);
                } else if (burckadin == 11 && burcerkek == 10) {
                    intent.putExtra("burcuyumu", balikkova);
                    intent.putExtra("number", 30);
                } else {
                    intent.putExtra("burcuyumu", balikbalik);
                    intent.putExtra("number", 100);
                }
                startActivity(intent);
            }
        });

        return rootView;
    }

    public class SpinnerAdapter extends ArrayAdapter<String> {

        public SpinnerAdapter(Context context, int textViewResourceId, String[] objects) {
            super(context, textViewResourceId, objects);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = getActivity().getLayoutInflater();
            View row = inflater.inflate(R.layout.spinner_row, parent, false);

            TextView text = (TextView) row.findViewById(R.id.textView1);
            text.setText(strings[position]);

            ImageView icon = (ImageView) row.findViewById(R.id.imageView1);
            icon.setImageResource(images[position]);

            return row;
        }

        @Override
        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = getActivity().getLayoutInflater();
            View row2 = inflater.inflate(R.layout.spinner_dropdown, parent, false);

            TextView text2 = (TextView) row2.findViewById(R.id.textView2);
            text2.setText(strings[position]);

            ImageView icon2 = (ImageView) row2.findViewById(R.id.imageView2);
            icon2.setImageResource(images[position]);

            return row2;
        }
    }
}