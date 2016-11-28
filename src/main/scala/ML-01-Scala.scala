// Databricks notebook source exported at Wed, 23 Nov 2016 21:05:25 UTC
// MAGIC %md
// MAGIC Krotki
// MAGIC ------
// MAGIC Krotka (ang. tuple) to ciąg N wartości określonego typu. W Scali można je tworzyc poprzez uzycie nawiasow okraglych, jak ponizej utworzono zmienna _dog_:

// COMMAND ----------

val dog = ("Burek", 5, true)

// COMMAND ----------

// MAGIC %md
// MAGIC #### Zadanie
// MAGIC Dokoncz definicje zmiennej _address_, tworzac krotke zawierajaca:
// MAGIC 1. Nazwe ulicy: Marszalkowska
// MAGIC 2. Numer domu: 16
// MAGIC 3. Numer mieszkania: 22
// MAGIC 4. Kod pocztowy: 00-212
// MAGIC 5. Miejscowosc: Warszawa
// MAGIC 
// MAGIC Kolejnośc obiektow jest istotna.

// COMMAND ----------

val address = ("Marszalkowska", 16, 22, "00-212", "Warszawa")

// COMMAND ----------

// MAGIC %md ### Pobieranie danych z krotki
// MAGIC Pobieranie danych można zrealizowac na dwa sposoby:
// MAGIC - poprzez pole _._N_ krotki - wybiera wartosc na _N._ pozycji krotki
// MAGIC - poprzez _unpacking_ - poprzez skladnie ponizej:

// COMMAND ----------

val (dogName, dogAge, _) = dog // Znak _ oznacza, by przy wypakowywaniu krotki pominac wartosc na tejze pozycji

// COMMAND ----------

// MAGIC %md #### Zadanie
// MAGIC Wypisz na wyjscie (poleceniem _println_ nastepujace wartosci z krotki _address_) przy uzyciu pol _._N_:
// MAGIC - kod pocztowy
// MAGIC - miejscowosc

// COMMAND ----------

println(address._4)
println(address._5)

// COMMAND ----------

// MAGIC %md #### Zadanie
// MAGIC Wypakuj i wypisz z krotki _address_ dane:
// MAGIC - ulica
// MAGIC - nr domu
// MAGIC - nr mieszkania

// COMMAND ----------

val (streetName, streetNo, premisesNo, _, _) = address
println(streetName)
println(streetNo)
println(premisesNo)

// COMMAND ----------

// MAGIC %md
// MAGIC Case class
// MAGIC ----------
// MAGIC - W przeciwienstwie do krotki, case class pozwala na narzucenie schematu (tj. nazw oraz typów wartości) na przechowywane w nich dane.
// MAGIC - W przeciwienstwie do zwyklej klasy, nie wymagaja pisania konstruktorow, getterow, setterow i mozna je porownywac operatorem ==.
// MAGIC 
// MAGIC Skladnia definicji klasy widoczna jest ponizej:

// COMMAND ----------

case class Vehicle(regNo: String, productionYear: Int, manufacturer: String, model: String, purchaseYear: Int, ownerName: String)
val vehicle = Vehicle("RX-39994", 2010, "Ford", "Focus", 2004, "Krzysztof")

// COMMAND ----------

// MAGIC %md ### Definicja klasy, tworzenie instancji
// MAGIC 
// MAGIC Zgodnie z przykładem, w definicji case class pola umieszczane sa wewnatrz nawiasu okraglego podanego po nazwie klasy (analogicznie do parametrow metody), konieczne jest podanie ich typów.
// MAGIC 
// MAGIC Instancje case class tworzy się bez użycia słowa kluczowego **new**.

// COMMAND ----------

// MAGIC %md #### Zadanie
// MAGIC Utwórz definicję klasy _Dog_ zawierającą pola:
// MAGIC - name (String)
// MAGIC - age (Int)
// MAGIC - breed (String)
// MAGIC 
// MAGIC Następnie utwórz trzy instancje tej klasy.

// COMMAND ----------

case class Dog(name: String, age: Int, breed: String)
val dog1 = Dog("Pieseł", 3, "Shiba")
val dog2 = Dog("Azor", 7, "Owczarek")
val dog3 = Dog("Mittens", 5, "Jamnik")

// COMMAND ----------

// MAGIC %md ### Klonowanie
// MAGIC 
// MAGIC Domyślnie wszystkie pola case classy są stałe (nie można zmieniac ich wartości). Zamiast tego, operacje na danych powinny zwraca _nowe_ instancje obiektów. W przypadku case class, aby uniknąc żmudnego przepisywania wartości pól, stosuje się klonowanie obiektu za pomocą metody _copy_. Nowe wartości pól podaje się w ramach argumentu funkcji (kolejnośc nie jest istotna, ale należy podac nazwy pól):

// COMMAND ----------

val soldVehicle = vehicle.copy(ownerName = "Ryszard", purchaseYear = 2016)

// COMMAND ----------

// MAGIC %md ### Zadanie
// MAGIC 
// MAGIC Dodaj każdemu psu 1 rok (tj. na podstawie istniejących, utwórz nowe egzemplarze klasy _Dog_).

// COMMAND ----------

val newDog1 = dog1.copy(age = dog1.age + 1)
val newDog2 = dog2.copy(age = dog2.age + 1)
val newDog3 = dog3.copy(age = dog3.age + 1)

// COMMAND ----------

// MAGIC %md ### Porównywanie obiektów
// MAGIC _Case class_ nie wymaga porównywania przy użyciu metody _equals_, można posługiwac się operatorem _=_. Obiekty są traktowane jako identyczne jeśli wszystkie ich pola są identyczne. Przykład:

// COMMAND ----------

val veh1 = Vehicle("RX-39994", 2010, "Ford", "Focus", 2004, "Krzysztof")
val veh2 = Vehicle("WX-03894", 2016, "Ford", "Transit", 1999, "Renata")
val veh3 = Vehicle("RX-39994", 2010, "Ford", "Focus", 2004, "Krzysztof")

assert(veh1 == veh1) // Object is always equals to itself
assert(veh1 != veh2) // Different contents, not equal
assert(veh1 == veh3) // Same values of object's fileds -> equal

assert(veh1 != veh1.copy(ownerName = "Andrzej"))

// COMMAND ----------

// MAGIC %md ### Pobieranie wartości
// MAGIC _Case class_ jest klasą, dlatego pobieranie wartości pól odbywa się identycznie jak w przypadku zwykłych klas.
// MAGIC Domyślnie wszystkie pola są zadeklarowane jako publiczne i niezmienne, dostęp do nich odbywa się bezpośrednio (bez użycia getterów).

// COMMAND ----------

println(vehicle.regNo)
println(vehicle.ownerName)

// COMMAND ----------

// MAGIC %md
// MAGIC Option
// MAGIC ------
// MAGIC Option opakowuje zmienna opcjonalna: moze byc zdefiniowana lub nie. Jest ona stosowana do tworzenia programów wolnych od NPE. Jej stosowanie poprawia również czytelnośc modelu - rozdział na pola obowiązkowe / opcjonalne jest zdefiniowany na poziomie kodu programu, a nie dokumentacji.

// COMMAND ----------

// MAGIC %md ### Tworzenie
// MAGIC Obiekty klasy _Option_ tworzy się używając obiektów:
// MAGIC - _Some(value)_ gdy wartośc jest obecna
// MAGIC - _None_ gdy nie jest.

// COMMAND ----------

// Meaning: class SimpleMail can be defined without attachment, but cannot be defined without from, to, subject.
case class SimpleMail(from: String, to: String, subject: String, attachment: Option[String])

val simpleMailWithAttachment = SimpleMail("foo@bar.com", "baz@bar.com", "SPAM Mail", Some("Buy our insurance!"))
val simpleMailWithoutAttachment = SimpleMail("abc@example.com", "baz@bar.com", "Other SPAM", None)

// COMMAND ----------

// MAGIC %md #### Zadanie
// MAGIC Utwórz definicje _case class_:
// MAGIC - Person: zawiera imię, nazwisko, zadatek (advance) i opcjonalnie nr telefonu (phone)
// MAGIC - Invoice: zawiera kwotę i opcjonalnie klienta (Person)
// MAGIC 
// MAGIC Następnie utwórz trzy egzemplarze klasy _Invoice_:
// MAGIC - jeden z klientem, klient z numerem telefonu,
// MAGIC - jeden z klientem, klient bez numeru telefonu,
// MAGIC - jeden bez klienta.

// COMMAND ----------

case class Person(firstName: String, lastName: String, advance: Int, phone: Option[String])
case class Invoice(price: Int, customer: Option[Person])

val invoiceWithPhone = Invoice(3329, Some(Person("Jan", "Kowalski", 2200, Some("993877222"))))
val invoiceWithoutPhone = Invoice(7721, Some(Person("Jan", "Kowalski", 100, None)))
val invoiceWithoutClient = Invoice(894, None)

// COMMAND ----------

// MAGIC %md ### Przetwarzanie (map, flatMap)
// MAGIC Klasa _Option[A]_ dostarcza metody _map(f)_ oraz _flatMap(f)_, służące do bezpiecznego operowania na przechowywanej w niej wartości (niezależnie od tego, czy jest ona obecna).
// MAGIC - Dla _None_ operacja zwraca _None_.
// MAGIC - Dla _Some(value)_ operacja _map_ zwraca _Some(f(value))_, a operacja _flatMap_ zwraca _f(value)_
// MAGIC 
// MAGIC #### Format zapisu funkcji _f_
// MAGIC Operacje takie, jak _map_, _flatMap_ wymagają podania jako parametru funkcji pobierającej parametr typu _A_ i zwracającej nową wartośc. Operacje te można zapisywac analogicznie do wyrażen lambda:
// MAGIC 
// MAGIC ```scala
// MAGIC parametr => wynik```
// MAGIC 
// MAGIC Jeżeli niezbędne jest bardziej rozbudowane wyrażenie, należy użyc nawiasów klamrowych:
// MAGIC 
// MAGIC ```scala
// MAGIC parametr => {
// MAGIC   val tmp1 = ...  
// MAGIC   val tmp2 = ...  
// MAGIC   val wynik = ...  
// MAGIC   wynik  
// MAGIC }```
// MAGIC 
// MAGIC Jako wynik funkcji przyjmuje się wartośc zwracaną przez ostatnie wyrażenie. Uwaga: operacja przypisania nie zwraca wyniku (jakiegokolwiek typu)!

// COMMAND ----------

val maybeMailWithAttachment: Option[SimpleMail] = Some(SimpleMail("foo@bar.com", "foo@baz.com", "This is subject", Some("Attachment content")))
val maybeMailWithoutAttachment: Option[SimpleMail] = Some(SimpleMail("foo@bar.com", "foo@baz.com", "This is subject", None))
val maybeNotMail: Option[SimpleMail] = None

// Map can safely extract field from a class inside Option
println(maybeMailWithAttachment.map(mail => mail.subject))
println(maybeNotMail.map(mail => mail.subject))

// Using map on option field of option class results in nested Options...
println(maybeMailWithAttachment.map(mail => mail.attachment))
// ...so flatMap use instead
println(maybeMailWithAttachment.flatMap(mail => mail.attachment))
println(maybeMailWithoutAttachment.flatMap(mail => mail.attachment))

// COMMAND ----------

// MAGIC %md #### Zadanie
// MAGIC Dla każdej z 3 faktur wykonaj:
// MAGIC - wypisz numer telefonu 
// MAGIC - wypisz kwotę zaliczki z doliczonym podatkiem (+23%)

// COMMAND ----------

println(invoiceWithPhone.customer.flatMap(c => c.phone))
println(invoiceWithoutPhone.customer.flatMap(c => c.phone))
println(invoiceWithoutClient.customer.flatMap(c => c.phone))

println(invoiceWithPhone.customer.map(c => c.advance * 1.27))
println(invoiceWithoutPhone.customer.map(c => c.advance * 1.27))
println(invoiceWithoutClient.customer.map(c => c.advance * 1.27))

// COMMAND ----------

// MAGIC %md ###Odczytywanie wartości (getOrElse)
// MAGIC Czasami następuje koniecznośc wypakowania wartości z _Option_. Wykorzystac do tego można metodę _getOrElse_. Zwraca ona:
// MAGIC - wartośc z klasy _Option_, jeżeli jest obecna
// MAGIC - wartośc przekazaną w parametrze w p. p.

// COMMAND ----------

println(simpleMailWithAttachment.attachment.getOrElse("-NO ATTACHMENT-"))
println(simpleMailWithoutAttachment.attachment.getOrElse("-NO ATTACHMENT-"))

// COMMAND ----------

// MAGIC %md #### Zadanie
// MAGIC Oblicz i wypisz wartości zaliczek dla faktur:
// MAGIC - _invoiceWithoutPhone_
// MAGIC - _invoiceWithoutClient_

// COMMAND ----------

val advance1 = invoiceWithoutPhone.customer.map(c => c.advance).getOrElse(0)
val advance2 = invoiceWithoutClient.customer.map(c => c.advance).getOrElse(0)

// COMMAND ----------

// MAGIC %md 
// MAGIC Kolekcje: lista (List[A])
// MAGIC -------------------------
// MAGIC 
// MAGIC Lista w języku Scala zdefiniowana jest w jako obiekt jednej z poniższych klas:
// MAGIC - Pustej listy (_Nil_),
// MAGIC - Listy niepustej, składającej się z wartości pierwszego elementu i ogona (reszty listy) w postaci listy.
// MAGIC 
// MAGIC Aby utworzyc liste, mozna wykorzystac skladnie _List(element1, element2, element3,...)_. Listy puste tworzy się za pomocą _List_ lub _List.empty[A]_.

// COMMAND ----------

{
  val listOfInts = List(3, 2, 6, 3, 9, 1, 3)
  val emptyList = List()
  val emptyListOfInts = List.empty[Int]
  
  println(listOfInts)
  println(emptyList)
  println(emptyListOfInts)
}

// COMMAND ----------

// MAGIC %md ### Zadanie
// MAGIC Utwórz listy (o przynajmniej 5 elementach):
// MAGIC - liczb całkowitych,
// MAGIC - ciągów znaków (nie krótszych niż 3 znaki).

// COMMAND ----------

val listOfInts = List(3, 5, 0, -9, 2, 6)
val listOfStrings = List("asdf", "qwerty", "zxcvb", "qazwsx", "123abc")

// COMMAND ----------

// MAGIC %md
// MAGIC Kolekcje: zbiór (Set[A])
// MAGIC ------------------------
// MAGIC Zbiór, analogicznie do definicji matematycznej, to nieuporządkowana kolekcja elementów bez powtórzen. W języku Scala zbiory tworzy się analogicznie do list:

// COMMAND ----------

{
  val setOfInts = Set(4, 23, 2, 1, 6, 4, 9, 5)
  val emptySetOfInts = Set.empty[Int]
  
  println(setOfInts)
  println(emptySetOfInts)
}

// COMMAND ----------

// MAGIC %md #### Zadanie
// MAGIC Utwórz zbiór liczb całkowitych:
// MAGIC - min. 5 elementów (bez powtórzen)

// COMMAND ----------

val setOfInts = Set(0, 2, 4, 6, 8, 10)

// COMMAND ----------

// MAGIC %md ### Złączanie kolekcji
// MAGIC Łączenie kolekcji tego samego typu jest realizowane przez operator _++_. Podobnie jak pozostałe operacje na kolekcjach, nie modyfikuje on istniejących obiektów, a jedynie tworzy nowy (wynikowy).
// MAGIC 
// MAGIC Listy w Scali posiadają również operator _:::_ również służący do konkatenacji (jest on wprowadzony analogicznie do innych języków funkcyjnych, np Haskella).

// COMMAND ----------

val newSetOfInts: Set[Int] = setOfInts ++ Set(9, 4, 10, -45, 59, 32)
val newListOfInts = List(14, -9, -2, 199, 309) ++ listOfInts
List(3, 2, 1) ::: List(6, 5, 4) // List only!

// COMMAND ----------

// MAGIC %md ### Iteracja po elementach kolekcji (foreach)
// MAGIC Do wykonania operacji na wszystkich elementach kolekcji Scala udostępnia metodę _foreach_:
// MAGIC ```scala
// MAGIC foreach(f: A => Unit)```
// MAGIC (Słowo kluczowe _Unit_ oznacza, że funkcja nie zwraca parametrów lub parametry zwracane są ignorowane przez _foreach_).

// COMMAND ----------

// Will apply println to each element of newSetOfInts
//  Equivalent to newSetOfInts.foreach(i => println(i))
List("Each", "word", "in", "separate", "line").foreach(println)

// COMMAND ----------

// MAGIC %md ### Filtrowanie i transformacja (filter, map, flatMap)
// MAGIC 
// MAGIC Podobnie jak w przypadku _Option_, kolekcje udostępniają metody _map(f)_ i _flatMap(f)_. Tworzą one nowe kolekcje, których elementami są rezultaty działania funkcji _f_ na elementach kolekcji wejściowej.
// MAGIC 
// MAGIC Metoda _filter(f)_ natomiast, jak nazwa wskazuje, zwraca kolekcję zawierającą wyłącznie te elementy, które spełniają warunek _f_ (tj. dla których funkcja _f_ zwróciła wartośc _true_).

// COMMAND ----------

val dividableByTwo = newSetOfInts.filter(i => i % 2 == 0)
val intsAndHalves = dividableByTwo.flatMap(i => Set(i / 2, i))

// COMMAND ----------

// MAGIC %md #### Zadanie
// MAGIC Na podstawie _newListOfInts_ utwórz listę zawierającą kwadraty oraz dwukrotności liczb z wejściowej listy.

// COMMAND ----------

val doublesAndSquares = newListOfInts.flatMap(i => List(i * i, i + i))

// COMMAND ----------

// MAGIC %md #### Zadanie 
// MAGIC Na podstawie newSetOfInts utwórz zbiór liczb, które są dzielnikami co najmniej jednej z dodatnich liczb ze zbioru.
// MAGIC #### Wskazówka: 
// MAGIC Zakres liczb od 0 do N można wygenerowac poprzez `(0 to N)`, wynik można przekształcic do znanej kolekcji za pomocą `.toSet` lub `.toList`.

// COMMAND ----------

val dividors = newSetOfInts.flatMap(i => {
  val from2toi = (2 to i)
  val onlyDividors = from2toi.filter(d => i % d == 0)
  val dividorsAsSet = onlyDividors.toSet
  dividorsAsSet
})

// COMMAND ----------

// MAGIC %md ### Redukcja (fold)
// MAGIC Redukcja polega na zastosowaniu podanej funkcji do "zwinięcia" kolekcji do jednej wartości. Taką funkcją może byc:
// MAGIC - dodawanie liczb (rezultatem wtedy będzie suma wszystkich liczb), 
// MAGIC - sprawdzanie warunku logicznego (w zależności od implementacji - suma lub iloczyn logiczny).
// MAGIC 
// MAGIC Dla funkcji redukującej definiuje się dwa parametry: bieżący element kolekcji (czyli pierwszy z elementów jeszcze nie przetworzonych) i akumulator (wynik przetworzenia dotychczasowych elementów). Przy pierwszym przebiegu redukcji stan kolekcji to wejściowa kolekcja, a akumulator to parametr _initialValue_ metody _fold_.
// MAGIC 
// MAGIC W implementacji stosuje się dwie metody: _foldLeft_ lub _foldRight_. Różnią się one kierunkiem przechodzenia po elementach kolekcji.

// COMMAND ----------

val sumOfInts = newSetOfInts.foldLeft(0)((acc, current) => current + acc)

// COMMAND ----------

// MAGIC %md ### Zadanie
// MAGIC Sprawdz za pomocą _fold_ ponizsze warunki na zbiorze _newSetOfInts_:
// MAGIC - czy każdy element jest większy od -100
// MAGIC - czy istnieje element parzysty

// COMMAND ----------

val forallOverMinus100 = newSetOfInts.foldLeft(true)((acc: Boolean, current: Int) => acc && (current > -100))
val anyPositive = newSetOfInts.foldLeft(false)((acc: Boolean, current: Int) => acc || (current % 2 == 0))

// COMMAND ----------

// MAGIC %md #### Zadanie
// MAGIC Oblicz średnią arytmetyczną wartości _newListOfInts_. Nie używaj _.count()_ ani _.sum()_.

// COMMAND ----------

val intsSum = newListOfInts.fold(0)((acc, current) => current + acc)
val intsCount = newListOfInts.fold(0)((acc, current) => acc + 1)
val avg = intsSum / intsCount

// COMMAND ----------

// MAGIC %md ### Kolekcje a Option
// MAGIC W trakcie implementacji mozna napotkac sytuacje, w której konieczne jest wypakowanie wartosci z _Collection[Option[A]]_ do _Collection[A]_. Koncepcyjnie jest to operacja utworzenia listy z elementow zdefiniowanych w ramach kolekcji wejściowej. Operacje te mozna wykonac za pomocą metody _.flatten_:

// COMMAND ----------

val listOfOptions = List(Some(2), Some(4), None, Some(1), None)
val listOfDefined = listOfOptions.flatten

// COMMAND ----------

// MAGIC %md
// MAGIC Kolekcje: mapa (Map[K, V])
// MAGIC --------------------------
// MAGIC 
// MAGIC Mapa w Scali do pewnego stopnia różni się od pozostałych kolekcji w Scali. Mimo że API kolekcji udostępnia takie same metody, jak w przypadku list i zbiorów, w przypadku map operuje się na krotkach typu `Tuple[K, V]`, a nie na samych wartościach.

// COMMAND ----------

// MAGIC %md ### Tworzenie, odczytywanie

// COMMAND ----------

// Tuples may be created via ->...
val days = Map(1 -> "Monday", 2 -> "Tuesday", 3 -> "Wednesday", 4 -> "Thursday", 5 -> "Friday", 6 -> "Saturday", 7 -> "Sunday")
// ...or explicitly
val seasons = Map((1, "Spring"), (2, "Summer"), (3, "Autumn"), (4, "Winter"))

// Values should be accessed via get(key) option to handle non-existing keys problems
println(seasons.get(2))
println(seasons.get(4))
println(seasons.get(6))

// COMMAND ----------

// MAGIC %md #### Zadanie
// MAGIC Utwórz mapę `capitals` zawierającą kraje (klucze) i odpowiadające im stolice (wartości). Wstaw minimum 5 krajów.

// COMMAND ----------

val capitals = Map(
  "Poland" -> "Warsaw",
  "Germany" -> "Berlin",
  "Russia" -> "Moscow",
  "China" -> "Pekin",
  "Japan" -> "Tokio"
)

// COMMAND ----------

// MAGIC %md ### Operacje (filtrowanie, transformacja)
// MAGIC Operacje na mapach można podzielic na trzy rodzaje:
// MAGIC - operacje na krotkach _(klucz, wartosc)_,
// MAGIC - operacje na kluczach.
// MAGIC - operacje na wartościach.
// MAGIC 
// MAGIC #### Filtrowanie
// MAGIC - wg wartości kluczy: `filterKeys(f)`
// MAGIC - wg krotek _(k, v)_: `filter(f)`
// MAGIC 
// MAGIC #### Mapowanie
// MAGIC - wartości: `mapValues(f)`
// MAGIC - krotek _(k, v)_: `map(f)`

// COMMAND ----------

// Here are using tuple unpacking for more clarity, hence 'case' keyword.
// However, using 'case' here forces using curly braces. 
val daysReverse = days.map { case (key, value) => (value, key) }

// More common use case: mapping only values
val daysFrom0 = daysReverse.mapValues(value => value - 1)

// Filtering by tuples...
val weekendTupleStd = days.filter(kv => kv._1 > 5)
val weekendTupleUnpacked = days.filter { case (key, value) => key > 5 }
val weekendKey = days.filterKeys(key => key > 5)

// COMMAND ----------

// MAGIC %md #### Zadanie
// MAGIC Na podstawie mapy `capitals` utwórz mapy:
// MAGIC - `capitalsInCapitals`, zawierającą stolice zapisane wielkimi literami
// MAGIC - `countries`, będącą odwróceniem mapy `capitals` (tj. kluczami będą wartości, a wartości - kluczami)

// COMMAND ----------

// MAGIC %md ### Generowanie ze zbiorów i pobieranie jako zbiory
// MAGIC 
// MAGIC #### Konwersja na mapę
// MAGIC Mapę można utworzyc z innej kolekcji za pomocą metody `.toMap` - wymaga to jednak, by kolekcja ta zawierała dwuelementowe krotki, które potem zostaną zamienione na pary _(klucz, wartośc)_.
// MAGIC 
// MAGIC #### Konwersja mapy na inną kolekcję
// MAGIC Podobnie jak w przypadku transformacji, wykonując konwersję mapy do innego rodzaju kolekcji można zastosowac jedno z trzech podejśc:
// MAGIC - konwersję na kolekcję krotek,
// MAGIC - konwersję tylko kluczy (zwracanych przez `.keys`)
// MAGIC - konwersję tylko wartości (zwracanych przez `.values`)
// MAGIC 
// MAGIC Uwaga: metody `.keys` i `.values` zwracają obiekty typu `Iterable`. Aby skorzystac ze zbioru / listy, należy wywołac odpowiednią metodę (`.toList` lub `.toSet`)

// COMMAND ----------

val daysAsTupleSet = days.toSet
val daysAsSet = daysAsTupleSet.map(t => t._2)
val daysAsSetKeys = daysReverse.keys.toSet
val daysAsSetValues = days.values.toSet

// COMMAND ----------

// MAGIC %md #### Zadanie
// MAGIC Na podstawie zbioru `moviesWithRating` utwórz mapę _nazwaFilmu -> ocena_.

// COMMAND ----------

val moviesWithRating = Set(
  ("Rambo", 4.2), 
  ("Jaws", 3.9),
  ("MIB", 4.0)
)

val movies = moviesWithRating.toMap

// COMMAND ----------

// MAGIC %md #### Na podstawie mapy `capitals` utwórz:
// MAGIC - zbiór panstw
// MAGIC - zbiór stolic

// COMMAND ----------

val countriesSet = capitals.values.toSet
val capitalsSet = capitals.keys.toSet
