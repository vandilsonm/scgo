<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div id="container_text">
    <div id="container_text_i">
        <h1><span class="corDestaque">Campeonato Brasileiro</span></h1>
    </div>
    <div id="container_text_resultados">
        <!-- dinamico -->
        <div class="table">
            <table border="0" cellpadding="6" cellspacing="0" class="gridListagem gridClassificacao">
                <thead>
                    <th colspan="2">Classificação</th>
                    <th><abbr title="Pontos">P</abbr></th>
                    <th><abbr title="Jogos">J</abbr></th>
                    <th><abbr title="Vitórias">V</abbr></th>
                    <th><abbr title="Empates">E</abbr></th>
                    <th><abbr title="Derrotas">D</abbr></th>
                    <th><abbr title="Gols Pró">GP</abbr></th>
                    <th><abbr title="Gols Contra">GC</abbr></th>
                    <th><abbr title="Saldo de Gols">SG</abbr></th>
                </thead>
                <tbody>
                <!-- loop do javascript aqui --
                    <tr>
                        <td>1</td>
                        <td>Cruzeiro</td>
                        <td>6</td>
                        <td>2</td>
                        <td>2</td>
                        <td>0</td>
                        <td>0</td>
                        <td>4</td>
                        <td>0</td>
                        <td>4</td>
                    </tr>
                    <tr>
                        <td>2</td>
                        <td>Atético</td>
                        <td>1</td>
                        <td>2</td>
                        <td>0</td>
                        <td>1</td>
                        <td>1</td>
                        <td>1</td>
                        <td>3</td>
                        <td>-2</td>
                    </tr>
                    <tr>
                        <td>2</td>
                        <td>América</td>
                        <td>1</td>
                        <td>2</td>
                        <td>0</td>
                        <td>1</td>
                        <td>1</td>
                        <td>1</td>
                        <td>3</td>
                        <td>-2</td>
                    </tr>
                    fim: loop do javascript aqui -->
                </tbody>
            </table>
        </div>
    <!-- dinamico -->
    </div>
</div>
<div id="container_right">
    <div id="container_right_ultimos_jogos"></div>
</div>