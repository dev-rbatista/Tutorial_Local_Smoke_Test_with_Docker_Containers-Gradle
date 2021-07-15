import React, { useContext, useEffect, useState } from 'react';
import AppContext from '../context/AppContext';
import {fetchProfile, updateName, changeUser} from '../context/Actions';

function Table() {
    const { state, dispatch} = useContext(AppContext);
    const {profile, name} = state;
    //const {loggeduser} = state;
    const [naming , setName ] = useState('');
    const { loading, error } = profile;
    const { data } = name;

    function handleSubmit(){
        //dispatch({ type: UPDATE_NAME, payload: { data: naming } })
        // OU
        dispatch(updateName(naming))
    }

    useEffect(() =>{
        /*
        dispatch(fetchUsersStarted());
        fetch(`${URL_API}/users`)
        .then(res =>  res.json())
        .then(res => dispatch(fetchUsersSuccess(res)))
        .catch(err => dispatch(fetchUsersFailure(err.message)))
        ;
        */
        //fetchNewProfile(dispatch,{/*loggeduser*/});
    },[]);

    console.log(name);

    if (loading === true) {
        return (
            <h1>Loading ....</h1>
    );
    }
    else {
        if (error !== null) {
            return (
                <div>
                    <button onClick={handleSubmit}>Press me please!</button>
                    <input value={naming} onChange={(event) => setName(event.target.value)}></input>
                    <h1>Error ....</h1>
                </div>
            );
        } else {
            //if (data.length > 0) {
                return (
                    <div>
                        <button onClick={()=>alert('valor and honour'+ document.getElementById('personID').value)}>Press me please!</button>
                        <input id='personID' type='submit' value='tonyze@latinlover.com'></input>
                        <button onClick={handleSubmit}>Press me please!</button>
                        <input value={naming} onChange={(event) => setName(event.target.value)}></input>
                        <h1>{data.name}</h1>
                        <p>{data.id}</p>
                        <h2><a href="http://vs116.dei.isep.ipp.pt:8080/categories">base de dados</a></h2>
                        <p>{data.birthdate}</p>
                        <h3>hamburger</h3>
                        <p></p>
                    </div>
                );
            //} else {
            //    return (<h1>No data ....</h1>);
            //}
        }
    }
}

//()=>alert('valor and honour'+ document.getElementById('personID').value)
//{()=>changeUser(dispatch,document.getElementById('personID').value)}

export default Table;