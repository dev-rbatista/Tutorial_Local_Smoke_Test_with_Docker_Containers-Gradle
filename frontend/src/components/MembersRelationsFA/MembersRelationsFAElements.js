import styled from 'styled-components';

export const MembersRelationsFADiv = styled.div`
  
  background-color: var(--backgroundGrey);
  overflow-x: hidden;
`

export const HeaderCell = styled.div`

  padding-left: 100px;

`

export const RelationsList = styled.td`
  display: ${ ({props}) => (props ? 'block' : 'none') } ;
  
`

