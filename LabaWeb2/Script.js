let arr = [
    {
        "id": 1,
        name: "Memoirs of a Geisha",
        "bookprice": "500",
        "pageQuantity": "350"
    },
    {
        "id": 2,
        name: "Sherlock Holmes",
        "bookprice": "625",
        "pageQuantity": "1150"
    },
    {
        "id": 3,
        name: "The Running Man",
        "bookprice": "555",
        "pageQuantity": "237"
    }
];
if (localStorage.getItem("book") == null)
    localStorage.setItem("book", JSON.stringify(arr));

let maintab = JSON.parse(localStorage.getItem("book"));

function viewall() {

    tab1.innerHTML = `<tr>
        <td width="1%">
            &nbsp;
        </td>
        <td width="13%">
            Номер
        </td>
        <td width="13%">
            Название книги
        </td>
        <td width="13%">
            Цена книги(Р)
        </td>
        <td width="13%">
            Количество страниц
        </td>
        <td width="4.5%">
            &nbsp;
        </td>
    </tr>`;
    for (let i = 0; i < maintab.length; i++) {

        let str = document.createElement("tr");
        let td1 = document.createElement("td");
        td1.innerHTML = "<input type='checkbox'>";
        str.appendChild(td1);
        let td2 = document.createElement("td");
        td2.innerHTML = maintab[i].id;
        str.appendChild(td2);
        let td3 = document.createElement("td");
        td3.innerHTML = maintab[i].name;
        str.appendChild(td3);
        let td4 = document.createElement("td");
        td4.innerHTML = maintab[i].price;
        str.appendChild(td4);
        let td5 = document.createElement("td");
        td5.innerHTML = maintab[i].pageQuantity;
        str.appendChild(td5);
        let td6 = document.createElement("td");
        td6.innerHTML = "<button onclick='deleteposition(" + maintab[i].id + ")'>Удалить</button>"
        str.appendChild(td6);
        tab1.appendChild(str);
    }
}

function deleteposition(id) {
    for (let i = 0; i < maintab.length; i++) {
        if (id == maintab[i].id) {
            maintab.splice(i, 1);
        }

    }
    viewall();
    localStorage.setItem("book", JSON.stringify(maintab));
}

function closeForm() {
    adder.style.display = "none";
}

function add() {
    let obj = {
        "id": maintab[maintab.length - 1].id + 1,
        name: name1.value,
        "bookprice": bookprice1.value,
        "pageQuantity": pageQuantity1.value,
    };
    maintab.push(obj);
    localStorage.setItem("book", JSON.stringify(maintab));


}

let idVhod = document.getElementById('idVhod');

function onclickVhod() {
    idVhod.style.display = (idVhod.style.display == 'inline') ? '' : 'inline'
    localStorage.setItem('hide', idVhod.style.display);

    if (localStorage.getItem('book') == 'inline') { // если значение объекта hide "inline"
        document.getElementById('idVhod').style.display = 'inline';

    }
}