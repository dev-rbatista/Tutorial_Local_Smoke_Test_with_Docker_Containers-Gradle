import styled from 'styled-components'

export const MainpageDiv = styled.div`

  
  
    // Implementar o grid system no Parent através da construção do esqueleto e nos childs definir as dimensões de cada secção.
    display: grid;
    height: 100vh;

    // Neste situação o "fr" indica a totalidade da tela
    grid-template-rows: 1fr;

    // Divisão da tela completa em dois grupos proporcionais, o primeiro de 1/4 do tamanho
    // e o segundo com 3/4 do tamanho. O "fr" indica essa proporção.
    grid-auto-columns: 1fr 3fr;
    overflow-y: hidden;
  
   
  
  
`