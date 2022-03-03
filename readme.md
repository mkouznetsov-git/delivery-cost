#Тестовая задача. Расчёт стоимости доставки груза.
##Пояснения
Для выполнения основной логики расчёта используется класс <code>DeliveryCostCalculator</code>.

Для запуска приложения используется класс <code>Main</code> с 4 параметрами:
1) <code>args[0]</code> -- расстояние до пункта назначения (Integer);
2) <code>args[1]</code> -- размер груза (Boolean: <code>true</code> -- большой, <code>false</code> -- небольшой);
3) <code>args[2]</code> -- фактор хрупкости груза (Boolean: <code>true</code> -- хрупкий, <code>false</code> -- нехрупкий);
4) <code>args[3]</code> -- степень загруженности службы доставки и соответствующий коэффициент-мультипликатор стоимости (String: <code>"normal"</code> -- 1.0, <code>"middle"</code> -- 1.2, <code>"high"</code> -- 1.4, <code>"highest"</code> -- 1.6);

DeliveryCostBlackBoxTest -- тесты для проверки логики по методу "чёрного ящика".

DeliveryCostWhiteBoxText -- тесты кода для проверки по методу "белого ящика".

##Условия
Стоимость рассчитывается в зависимости от следующих условий.

###*Расстояние до пункта назначения*

- более 30 км: +300 рублей к доставке;
- до 30 км: +200 рублей к доставке;
- до 10 км: +100 рублей к доставке;
- до 2 км: +50 рублей к доставке.

###*Габариты груза*

- большие габариты: +200 рублей к доставке;
- маленькие габариты: +100 рублей к доставке;

###*Хрупкость груза*

- если груз хрупкий: +300 рублей к доставке; 
- хрупкие грузы нельзя возить на расстояние более 30 км.

###*Загруженность службы доставки*
Стоимость умножается на коэффициент доставки:
- очень высокая загруженность ("highest") — 1.6;
- высокая загруженность ("high") — 1.4;
- повышенная загруженность ("middle") — 1.2;
- во всех остальных случаях ("normal") коэффициент равен 1.

###*Дополнительные условия*
- Минимальная сумма доставки — 400 рублей; если сумма доставки меньше минимальной, выводится минимальная сумма.

###*Входные параметры*
- расстояние до пункта назначения;
- габариты;
- информацию о хрупкости;
- загруженность службы на текущий момент

###*Результат на выходе*
- Пользователь получает стоимость доставки.