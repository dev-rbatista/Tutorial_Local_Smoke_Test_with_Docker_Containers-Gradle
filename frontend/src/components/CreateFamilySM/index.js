import React, {useContext, useState} from 'react';
import AppContext from "../../context/AppContext";
import {createFamilySM} from "../../context/Actions";
import {FormControl, FormGroup, FormLabel} from "react-bootstrap";
import Loading from "../Loading";
import "../../styles/createFamily.css"
import '../../styles/global.css';
/*import CreateFamilyForm, {
    emailID,
    name,
    birthDate,
    vatNumber,
    phone,
    street,
    city,
    houseNumber,
    zipCode,
    familyName,
    registrationDate
} from "./CreateFamilyForm";*/

export default function CreateFamilySM() {
    const {state, dispatch} = useContext(AppContext);
    const {createdfamily, jwt} = state;
    const {loading, error, data} = createdfamily;
    //Como é um POST vamos apenas ter um Estado Local do Component sem englobar o estado global da App neste pedido. Não é preciso
    //guardar no estado da App algo que vai ser enviado para o Backend.
    const [emailID, setEmailID] = useState('');
    const [name, setName] = useState('');
    const [birthDate, setBirthdate] = useState('');
    const [vatNumber, setVatNumber] = useState('');
    const [phone, setPhoneNumber] = useState('');
    const [street, setStreet] = useState('');
    const [city, setCity] = useState('');
    const [houseNumber, setHouseNumber] = useState('');
    const [zipCode, setZipCode] = useState('');
    const [password, setPassword] = useState('');
    const [familyName, setFamilyName] = useState('');
    const [registrationDate, setRegistrationDate] = useState('');


    const [display, setDisplay] = useState(true);

    // [] -> Vazio, quando o componente é montado (1ª vez)
    // sem o [] -> sempre que há um Update qualquer ao próprio componente
    // [aqui dentro definir o que vai fazer com que seja feito update] -> no caso de apenas querer modificar estado quando um campo específico é mudado.
    // Com return incluído depois da arrow function, é aplicado o que lá estiver quando o component é "unmounted"


    async function handleSubmit() {
        const createFamily = {
            emailID: emailID,
            name: name,
            birthDate: birthDate, vatNumber: vatNumber, phone: phone, street: street,
            city: city, houseNumber: houseNumber, zipCode: zipCode, password: password, familyName: familyName,
            registrationDate: registrationDate
        }
        createFamilySM(dispatch, createFamily,jwt.jwt)

    }

    function changeDisplay() {
        setDisplay(!display)
    }

    function submitAndChangeDisplay() {
        handleSubmit().then(changeDisplay());
    }

    function cleanStateAndChangeDisplay() {
        cleanState()
        changeDisplay();
    }

    function cleanState() {
        setEmailID('');
        setName('');
        setBirthdate('');
        setVatNumber('');
        setPhoneNumber('');
        setStreet('');
        setCity('');
        setHouseNumber('');
        setZipCode('');
        setPassword('')
        setFamilyName('');
        setRegistrationDate('');
    }

    //target.value -> permite que cada valor introduzido seja acrescentado ao valor anterior. Se for apagado algum dos valores, altera também o estado atual da variável.
    // Consultar Manuel Almeida

    if (display) {
        return (
            <div>
                <form className="center-form">
                    <FormGroup controlId="emailID">
                        <FormLabel className="font-class">Email: </FormLabel>
                        <br></br>
                        <FormControl className="createfamily-input" type="text" placeholder="Insert email here"
                                     onChange={emailID => setEmailID(emailID.target.value)}/>
                    </FormGroup>

                    <FormGroup controlId="emailID">
                        <FormLabel className="font-class">Password: </FormLabel>
                        <br></br>
                        <FormControl className="createfamily-input" type="password" placeholder="Insert password here"
                                     onChange={password => setPassword(password.target.value)}/>
                    </FormGroup>

                    <FormGroup controlId="adminName">
                        <FormLabel className="font-class">Administrator Name: </FormLabel>
                        <br></br>
                        <FormControl className="createfamily-input" type="text" placeholder="Insert Admin name"
                                     onChange={name => setName(name.target.value)}/>
                    </FormGroup>

                    <FormGroup controlId="birthdate">
                        <FormLabel className="font-class">Birthdate: </FormLabel>
                        <br></br>
                        <FormControl className="createfamily-input" type="text" placeholder="Insert birthdate here"
                                     onChange={birthdate => setBirthdate(birthdate.target.value)}/>
                    </FormGroup>

                    <FormGroup controlId="vatnumber">
                        <FormLabel className="font-class">Vat Number: </FormLabel>
                        <br></br>
                        <FormControl className="createfamily-input" type="text" placeholder="Insert Vat Number here"
                                     onChange={vatNumber => setVatNumber(vatNumber.target.value)}/>
                    </FormGroup>

                    <FormGroup controlId="phone">
                        <FormLabel className="font-class">Phone Number: </FormLabel>
                        <br></br>
                        <FormControl className="createfamily-input" type="text" placeholder="Insert Phone number here"
                                     onChange={phone => setPhoneNumber(phone.target.value)}/>
                    </FormGroup>

                    <FormGroup controlId="street">
                        <FormLabel className="font-class">Street: </FormLabel>
                        <br></br>
                        <FormControl className="createfamily-input" type="text" placeholder="Insert street here"
                                     onChange={street => setStreet(street.target.value)}/>
                    </FormGroup>

                    <FormGroup controlId="city">
                        <FormLabel className="font-class">City: </FormLabel>
                        <br></br>
                        <FormControl className="createfamily-input" type="text" placeholder="Insert city here"
                                     onChange={city => setCity(city.target.value)}/>
                    </FormGroup>

                    <FormGroup controlId="houseNumber">
                        <FormLabel className="font-class">House Number: </FormLabel>
                        <br></br>
                        <FormControl className="createfamily-input" type="text" placeholder="Insert house number here"
                                     onChange={houseNumber => setHouseNumber(houseNumber.target.value)}/>
                    </FormGroup>

                    <FormGroup controlId="zipCode">
                        <FormLabel className="font-class">Zip Code: </FormLabel>
                        <br></br>
                        <FormControl className="createfamily-input" type="text" placeholder="Insert zip code here"
                                     onChange={zipCode => setZipCode(zipCode.target.value)}/>
                    </FormGroup>

                    <FormGroup controlId="familyName">
                        <FormLabel className="font-class">Family Name: </FormLabel>
                        <br></br>
                        <FormControl className="createfamily-input" type="text" placeholder="Insert Family Name"
                                     onChange={familyName => setFamilyName(familyName.target.value)}/>
                    </FormGroup>

                    <FormGroup controlId="registrationDate">
                        <FormLabel className="font-class">Registration Date: </FormLabel>
                        <br></br>
                        <FormControl className="createfamily-input" type="text"
                                     placeholder="Insert registration date here"
                                     onChange={registrationDate => setRegistrationDate(registrationDate.target.value)}/>
                    </FormGroup>
                </form>
                <br></br>
                <div className="button-position">
                    <button className="button-two-small" onClick={submitAndChangeDisplay}>Register Family</button>
                </div>
            </div>
        )
    } else if (!display && loading === true) {
        return (
            <Loading/>
        )

    } else if (!display && error !== null) {

        if (createdfamily.error === "Request failed with status code 422") {
            return (
                <div style={{textAlign: "center"}}>
                    <p> One of the fields was incorrect. Please try again. </p>
                    <div className="button-position">
                        <button className="button-two-small" onClick={cleanStateAndChangeDisplay}>Try again</button>
                    </div>
                </div>
            )
        } else {
            return (
                <div style={{textAlign: "center"}}>
                    <p> Oops! Something unexpected happened. Please try again. </p>
                    <div className="button-position">
                        <button className="button-two-small" onClick={cleanStateAndChangeDisplay}>Try again</button>
                    </div>
                </div>
            )
        }
    } else {
        return (
            <>
                <br></br>
                <table>
                    <tr>
                        <th>Family Name</th>
                        <td>{data.familyName}</td>
                    </tr>
                    <br></br>
                    <tr>
                        <th>Family Admin ID</th>
                        <td>{data.adminID}</td>
                    </tr>
                    <br></br>
                    <tr>
                        <th>Family Registration Date</th>
                        <td>{data.registrationDate}</td>
                    </tr>
                    <br></br>
                </table>
                <div className="button-position">
                    <button className="button-two-small" onClick={cleanStateAndChangeDisplay}>Create another family
                    </button>
                </div>
            </>
        )
    }


}


/*
Alternativa aos passos "singulares"
const [createfamilystate, setfamilystate] = useState({
    emailID: '',
    name: '',
    birthdate: '',
    vatNumber: '',
    phone: '',
    street: '',
    city: '',
    houseNumber: '',
    zipCode: '',
    familyName: '',
    registrationDate: ''
})

 */