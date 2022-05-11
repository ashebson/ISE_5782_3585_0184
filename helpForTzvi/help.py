import parse
TRIANGLE_FORMAT = """<triangle>
           <v1>{}</v1> <v2>{}</v2> <v3>{}</v3>
        </triangle>"""
VERTEX_FORMAT = """<vertex><coordinates>
           <x>{}</x> <y>{}</y> <z>{}</z>
        </coordinates></vertex>"""
JAVA_VERTEX_FORMAT = """new Point({}, {}, {})"""
JAVA_TRIANGLE_FORMAT = """\t\tnew Triangle({}, {}, {}).setMaterial(material),\n"""

FUNCTION_HEADER = """private Geometry[] getTriangles{}() {{
    Geometry[] triangles = {{\n"""

FUNCTION_FOOTER = """\t};
    return triangles;
}\n"""

darth_data = open(
    "/Users/aryehshebson/Desktop/Degree/Minip SE/Project/ISE_5782_3585_0184/helpForTzvi/darth.xml", 'r').read()
triangles = list(parse.findall(TRIANGLE_FORMAT, darth_data))
formatted_triangles = [(int(i[0]), int(i[1]), int(i[2])) for i in triangles]
vertecies = list(parse.findall(VERTEX_FORMAT, darth_data))
formatted_vertecies = [(float(i[0]), float(i[1]), float(i[2])) for i in vertecies]
java_vertecies = [JAVA_VERTEX_FORMAT.format(i[0], i[1], i[2]) for i in formatted_vertecies]
final = ""
function_counter = 0
for i,triangle in zip(range(len(formatted_triangles)),formatted_triangles):
    if i == 0:
        final += FUNCTION_HEADER.format(function_counter)
    elif i%500 == 0:
        function_counter += 1
        final += FUNCTION_FOOTER
        final += FUNCTION_HEADER.format(function_counter)
    vertex1index = triangle[0]
    vertex2index = triangle[1]
    vertex3index = triangle[2]
    final += JAVA_TRIANGLE_FORMAT.format(java_vertecies[vertex1index], java_vertecies[vertex2index], java_vertecies[vertex3index])
    if i == len(formatted_triangles)-1:
        final += FUNCTION_FOOTER
open("formatted_vader.txt",'w').write(final)
