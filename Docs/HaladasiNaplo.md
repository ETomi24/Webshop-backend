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


