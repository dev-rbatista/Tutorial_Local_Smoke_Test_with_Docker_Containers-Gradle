import styled from "styled-components";


export const LoadingDiv = styled.div`
  background-color: lightgray;
  height: 100vh;
  width: 100%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  box-shadow: inset 0 0 0 1000px rgba(0,0,0, 0.2);
  object-fit: contain;
`

export const LoadingInnerDiv = styled.div`
  color: #fff;
  font-size: 100px;
  margin-top: 0px;
`