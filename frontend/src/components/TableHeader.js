import React, { useContext } from 'react';
import AppContext from '../context/AppContext';

function TableHeader() {
    const {headers} = useContext(AppContext);

    return(
        <thead>
        <tr>
            <th style={{width:"50px"}}>{headers.id}</th>
            <th style={{width:"200px"}}>{headers.name}</th>
            <th style={{width:"200px"}}>{headers.username}</th>
            <th style={{width:"300px"}}>{headers.email}</th>
        </tr>
        </thead>
    );
}
export default TableHeader;