# TP-IAE

# Thibault Courel - Nicolas Auguste - ZZ3 F2

# TP1

Nous avons utilisé autant que  possible l'injection de dépendances. Il y a possibilité d'injecter différentes batteries/propulseurs, auquel l'ambigiuëté est levée avec des qualifiers (annotation java/fr.elfoa.qualifiers).


# TP2

Nous avons créé des classes à mapper avec la BDD (java/fr.elfoa.tp2). Elles sont donc annotées @Entity avec un Id auto-généré.
Deux tests permettent de vérifier que la persistence fonctionne, y compris en cascade (Un 'student' agrège une 'school').