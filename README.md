# Academy_Scheduling

Ծրագիրը նախատեսված է Picsart Academy-ի 1-ին հարկի ընդհանուր օգտագործման նստատեղերի ամրագրման(seat booking) համար։
Ծրագրի իրականացման ընթացքում օգտագործվել է Java ծրագրավորման լեզուն, MySQL տվյալների բազան, JavaFX framework-ը ծրագրի արտաքին տեսքի
և User Interface-ի ստեղծման և ձևավորման համար, Scene Builder ծրագիրը՝ որպես UI կոնստրուկտոր, որը ավտոմատ գեներացնում է JavaFX կոդ և
JDBC(Java DataBase Connection)-ը MySQL տվյալների բազայի հետ ծրագրի կապ հաստատելու համար։ Ծրագիրը իրականացված է որպես Desktop application:

Օգտագործողը ծրագիրը բացելուց հետո կտեսնի պատուհան, որտեղ կարող է մուտք գործել իր մուտքանունը(username, email կամ phone) և գաղտնաբառը ներմուծելով։
Եթե գրանցված account չունի, կարող է գրանցվել, սեղմելով ներքևի sign up կոճակը և այնտեղ լրացնելով բոլոր դաշտերը պատշաճ կերպով։
Մուտք գործելուց հետո բացվում է ծրագրի հիմնական էջը, որտեղ երևում է ակադեմիայի 1-ին հարկի նստատեղերի քարտեզը՝ կանաչով նշված են այս պահի ազատ նստատեղերը, իսկ կարմիրով
այս պահին զբաղվածները։ օգտագործելով այդ էջում տեղադրված օրացույցը և ընտրելով ժամը, կարելի է տեսնել կոնկրետ այդ օրվա այդ ժամի ազատ և զբաղված աթոռները։
ներքևի աջ մասում պատկերվում է օգտագործողի տվյալները, իսկ դրանից վերև ցույց է տրվում ամրագրված աթոռի համարը, ամրագրման սկիզբը և ավարտը։ այդ մասի 
cancel կոճակով կարելի է չեղարկել ամրագրումը։ Log out կոճակով դուրս կգաք account-ից և կհայտնվեք առաջին էջում։
Սեղմելով ցանկացած աթոռի վրա, կբացվի պատուհան, որտեղ կերևա այդ աթոռի համար այսօրվա ամրագրումները, իսկ այդ պատուհանում օրացույցի միջոցով ընտրելով օրը
և սեղմելով check it կոճակը, կտեսնեք ընտրված օրվա ամրագրումները։ Ընտրելով օրը և ամրագրման սկզբի և ավարտի ժամերը, և սեղմելով reserve կոճակը, կամրագրեք ընտրված աթոռը,
եթե այն մինչ այդ ամրագրված չի եղել այդ ժամի համար։

Չի կարելի ամրագրել նստատեղ մինչ ներկա պահի, կամ մեկ շաբաթից ավել օրվա համար։ Չի կարելի նաև տեսնել այդ օրերի համար ամրագրումները։ Չի կարելի ունենալ 1 ամրագրումից ավելի։
Առավելագույն ամրագրման ժամանակը 1 օր է։

Ծրագրի նորմալ աշխատանքի համար source code ունենալու դեպքում անհրաժեշտ են՝

Intellij IDEA, 
JDBC library կցված պրոեկտին, 
JavaFX library կցված պրոեկտին, 
MySQL DB, որը ստեղծված կլինի ճիշտ նույն ձևով, ոնց ծրագրի հեղինակն է ստեղծել։

//Խորհուրդ չի տրվում այդպես աշխատացնել ծրագիրը, քանի որ ծրագրի իրական աշխատանքի համար Server-ը local-ից պետք է տեղափոխվի․․․