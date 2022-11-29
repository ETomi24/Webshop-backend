# Ernyei Tamás - Webshop készítése microservice alapokon
# Haladási napló


## 1 Hét
Első héten még nagyon bizonytalan voltam ezért kértem is egy plusz megbeszélést ahol sikeresen dűlőre jutottunk,
hogy a Microservices arhitektúra rejtelmeiben fogok elmerülni a félév során.
Konzultáció  után elkészítettem 3 specifikációt hogy a következő konzultáción ezek közül egyet ki lehessen majd választani.

## 2 hét
A konzultáción sikeresen kiválasztottunk a WebShop ötletét a 3 specifikáció közül.Ezután elkészítettem a feladatkiírást is.
Ezen a héten még elkészítettem a képernyőképeket és az adatmodelt is.

## 3 hét
Ezen héten tovább ismerkedtem a microservice arhitektúrával rengeteg cikket és videot néztem róla.
A hét folyamán elkezdtem a dockert megismerni de ennek telepítésének során közbe egy kis baki ami miatt tönkre ment a gépem....
Sikerült elkészítenem a serviceket és eldöntöttem hogy elöször eureka-t hasznáklok majd később ha marad idő átállok kubernetesre.

## 4.hét
Ezen a héten elkészítettem az adatréteget minden servichez.Ennek során felmerült bennem egy olyan gondolat,
hogy a cartban ami az ábrán a contains kapcsolat a product és az order között, hogy ezt a userrel kéne összekötni és az orderId-t
csak akkor megadni amikor tényleg rendelés történik.Ezen a problémán még gondolkozok, hogy mi lenne a jobb, az aktuális, vagy az, hogy a userrel is összekötöm.
A servicekben elkezdtem írni a logikát ez egy két helyen már elég jól áll.
Az alkalmazásban elkezdtem használni az OpeinFeign klienseket ami a restTempletet helyettesiti és ezzel tudom csökkenti az alkalmazás komplexitását.
Elkészítettem ezen felül az apiGatewayt is, ahonnan egy helyről elérem az összes service-t. Ezen keresztül fogom majd elérni a szervert az Angular kliensnből.

## 5.hét
Az 5.héten az alkalmazás biztonságossá tételén ügyködtem.Ezt jwt token használatával probálom elérni.

## 6.hét
Az aktuális héten a befejeztem nagyjából a servicek kontrollerek vázának irását innen már csak finom hangolni kell rajtuk.
Csináltam egy auth servert aminek feladata JWT token ellöállítása és validálása.
Frontenden verziót frissitettem mert még egy régebbi verzióval generáltam ennek során felmerültek problémák,
de végül sikerült megoldani.
A frontenden mégcsak a navbaron dolgozok de már itt felmerült egy probléma hogy egy dropdown elem nem műkődik rendesen

## 7.hét
A héten a backenden kellet foltozgatni egy két hibát és kimaradó funkciót elkészíteni.Frontenden elkesztem készíteni az appot.
A bejelentkezés regisztráció váza késze van.Admin felületet kesztem el készíteni még a héten.


## 8.hét
A héten összekötöttem a backendet és a frontendent egymással, ami kezdetben sok időt elvitt, mivel a cors beállítok nem akartak rendensen működni.
Végül az lett a megoldás hogy nem szervizenkétn kell egyenkét a cors beállításokat felvenni hanem az apiGatewayben.
Elkészítettem ezentúl az admin felületet ahol termékeket lehet felvenni illetve törölni vagy módósítani.
Mostantól tárolok már képeket is a backenden hogy színesebb legyen a frontenden egy-egy termék.

## 9.hét
A héten probáltam szépíteni a frontendet. Pár oldal kivételével minden elkészült. Backenden pedig a hiányoságokat,
amikre szükség volt azokat potoltam. 

## 10.hét
A héten befejeztem a jwt tokennek az implementációját, ez annyit takar hogy mostantól minden szervízben használom azt authorizációhoz.
A frontenden is már szerepekhez van kötve a hozzáférés bizonyos oldalakhoz
## 11.hét
Frontenden csináltam pár validációt a dolgozatnak az elso 4 fejezetét megirtam
## 12.hét
A dolgozat maradék részét megirtam
