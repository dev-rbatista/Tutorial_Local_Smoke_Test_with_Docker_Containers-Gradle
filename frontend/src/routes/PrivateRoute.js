import React, {useContext} from 'react';
import {Redirect, Route} from 'react-router-dom';
import AppContext from "../context/AppContext";


function PrivateRoute({component: Page, ...rest}) {
    const {state} = useContext(AppContext);
    const {loggedUser} = state;
    const {id, role} = loggedUser;
    return (
        <Route  {...rest} render={(props) => (
           role !== '')
            ? (<Page {...props} />)
            : (<Redirect to="/login"/>)}
        />
    );
}

export default PrivateRoute;