var json;
var parse_place_id = "parse-here";
var sort_by = "gender";

function SendLogPass(){
    var ajax = new XMLHttpRequest();
    var xxx = document.getElementById("str1").value;
    var zzz = document.getElementById("str2").value;
    ajax.open('POST', 'http://127.0.0.1:8080/JEElesson/login', false);
    json = new Object();
    json.login = xxx;
    json.password = zzz;
    ajax.send(JSON.stringify(json));
    var yyy =  ajax.responseText;
    alert(yyy);
}

function CreateStr() {
    var a = document.createElement('p');
    a.innerHTML = "NewSTR";
    var b = document.getElementById('parse-here');
    b.appendChild(a);
}

function getDataFromServer() {
    var ajax = new XMLHttpRequest();
    ajax.open('GET', 'http://smart-route.ru:8100/adapter-web/rest/dictionary/c580d006-f86f-4bdd-84be-b51de6f626c6', false);
    ajax.send();
    return ajax.responseText;
}

function parseData() {
    var data = getDataFromServer();
    json = JSON.parse(data).documents;
    return json;
}

function setStrings() {
    var b = document.getElementById(parse_place_id);
    b.innerHTML = "";
    json_sort();
    for (var i = 0; i < json.length; i++) {
        var a = document.createElement('p');
        a.innerHTML = json[i].fio + " " + json[i].gender + " " + json[i].birthDate + " " + json[i].phone;
        b.appendChild(a);
        a.id = "row-"+i; // Добавляем к строкам ID для того чтобы показаывать их при поиске
    }

    search(document.getElementById("search_input"));
    currentPage = 0;
}

function json_sort(){

    var tmp = {};

    for(var i=0; i < json.length; i++){
        tmp[json[i][sort_by]+i] = i;
    }

    tmp = ksort(tmp);

    var new_json = [];
    var n = 0;
    for (var i in tmp) {
        new_json[n++] = json[tmp[i]]
    }
    json = new_json;

}

function search(elem){  // В функцию передается указатель на элемент в котором храниться строка для поиска

    var $s = elem.value.toLowerCase(); // Получаем значение инпута и приводим в нижний регистр (ДАБЫ СКРИПТ БЫЛ НЕЧУВСТВИТЕЛЕН К РЕГИСТРУ СИМВОЛОВ)

    var $children = document.getElementById(parse_place_id).children; // Находим все дочерние у блока в котором все данные

    if($s != ''){ // Проверка на пустую строку

        for(var i=0;i<$children.length;i++){$children[i].style.display = "none";} // Скрываем все дочерние

        for(var i=0;i<json.length;i++){  // Проходим весь массив
            if(json[i].fio.toLowerCase().indexOf($s) + 1) { // Поиск подстроки в строке все с нижним регистром (ДАБЫ СКРИПТ БЫЛ НЕЧУВСТВИТЕЛЕН К РЕГИСТРУ СИМВОЛОВ)
                document.getElementById("row-"+i).style.display = "block"; // Показываем по ID
            }
        }
    }else{
        for(var i=0;i<$children.length;i++){
            $children[i].style.display = "block"; // Показываем все дочерние
        }
    }
}

function change_sorting(elem){
    sort_by = elem.options[elem.selectedIndex].value;
    console.log("searching set to: "+sort_by);
    setStrings();
}

function ksort(w) { // функция для сортировки по ключам
    var sArr = [], tArr = [], n = 0;

    for (i in w){
        tArr[n++] = i;
    }

    tArr = tArr.sort();
    for (var i=0, n = tArr.length; i<n; i++) {
        sArr[tArr[i]] = w[tArr[i]];
    }
    return sArr;
}
