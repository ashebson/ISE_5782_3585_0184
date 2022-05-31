import parse
OLD_TRIANGLE_FORMAT = """<triangle>
           <v1>{}</v1> <v2>{}</v2> <v3>{}</v3>
        </triangle>"""
OLD_VERTEX_FORMAT = """<vertex><coordinates>
           <x>{}</x> <y>{}</y> <z>{}</z>
        </coordinates></vertex>"""
NEW_VERTEX_FORMAT = """{} {} {}"""
MATERIAL = '<primitives.Material kD="{}" kS="{}" nShininess="{}"/>'.format(
    NEW_VERTEX_FORMAT.format(0.3, 0.3, 0.3), NEW_VERTEX_FORMAT.format(0.8, 0.8, 0.8), 30)
AMBIENT_LIGHT = '<ambient-light color="{} {} {}"/>'.format(38.25, 38.25, 38.25)
NEW_TRIANGLE_FORMAT = '\t\t<geometries.Triangle p0="{}" p1="{}" p2="{}">\n\t\t\t{}\n\t\t</geometries.Triangle>\n'

NEW_HEADER = '<scene name="vader-scene">\n\t{}\n\t<geometries>\n'

NEW_FOOTER = '\t</geometries>\n</scene>'

darth_data = open(
    "/Users/aryehshebson/Desktop/Degree/Minip SE/Project/ISE_5782_3585_0184/helpForTzvi/darth.xml", 'r').read()
triangles = list(parse.findall(OLD_TRIANGLE_FORMAT, darth_data))
formatted_triangles = [(int(i[0]), int(i[1]), int(i[2])) for i in triangles]
vertecies = list(parse.findall(OLD_VERTEX_FORMAT, darth_data))
formatted_vertecies = [(float(i[0]), float(i[1]), float(i[2]))
                       for i in vertecies]
new_vertecies = [NEW_VERTEX_FORMAT.format(
    i[0], i[1], i[2]) for i in formatted_vertecies]
final = ""
function_counter = 0
final += NEW_HEADER
for i, triangle in zip(range(len(formatted_triangles)), formatted_triangles):
    vertex1index = triangle[0]
    vertex2index = triangle[1]
    vertex3index = triangle[2]
    final += NEW_TRIANGLE_FORMAT.format(new_vertecies[vertex1index],
                                        new_vertecies[vertex2index], new_vertecies[vertex3index], MATERIAL)
final += NEW_FOOTER
open("formatted_vader_2.xml", 'w').write(final)
