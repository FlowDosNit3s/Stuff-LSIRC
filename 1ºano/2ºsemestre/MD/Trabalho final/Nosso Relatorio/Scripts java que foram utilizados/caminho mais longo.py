import heapq
import pandas as pd

def convert_matrix_to_graph(matrix, labels):
    graph = {}
    size = len(matrix)
    for i in range(size):
        node = labels[i]
        graph[node] = {}
        for j in range(size):
            if matrix[i][j] != 0:
                graph[node][labels[j]] = matrix[i][j]
    return graph

def dijkstra(graph, start):
    distances = {vertex: float('infinity') for vertex in graph}
    previous_vertices = {vertex: None for vertex in graph}
    distances[start] = 0
    priority_queue = [(0, start)]

    while priority_queue:
        current_distance, current_vertex = heapq.heappop(priority_queue)

        if current_distance > distances[current_vertex]:
            continue

        for neighbor, weight in graph[current_vertex].items():
            distance = current_distance + weight

            if distance < distances[neighbor]:
                distances[neighbor] = distance
                previous_vertices[neighbor] = current_vertex
                heapq.heappush(priority_queue, (distance, neighbor))

    return distances, previous_vertices

def construct_path(previous_vertices, start, vertex):
    path = []
    current_vertex = vertex
    while current_vertex is not None:
        path.insert(0, current_vertex)
        current_vertex = previous_vertices[current_vertex]
    if path[0] == start:
        return path
    else:
        return []

matrix = [
    [0, 0, 0, 0, 0, 0, 5, 0, 0, 0, 0, 19],
    [0, 0, 0, 0, 0, 0, 28, 14, 0, 0, 0, 0],
    [0, 0, 0, 0, 0, 0, 0, 0, 11, 27, 0, 0],
    [0, 0, 0, 0, 0, 0, 0, 0, 0, 18, 0, 0],
    [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 20, 23],
    [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9, 7],
    [5, 28, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
    [0, 14, 0, 0, 0, 0, 0, 0, 7, 0, 0, 15],
    [0, 0, 11, 0, 0, 0, 0, 7, 0, 0, 0, 0],
    [0, 0, 27, 18, 0, 0, 0, 0, 0, 0, 6, 0],
    [0, 0, 0, 0, 20, 9, 0, 0, 0, 6, 0, 0],
    [19, 0, 0, 0, 23, 7, 0, 15, 0, 0, 0, 0]
]

labels = ['1', '2', '3', '4', '5', '6', 'ALO', 'INTER1', 'INTER2', 'INTER3', 'INTER4', 'INTER5']

graph = convert_matrix_to_graph(matrix, labels)

start_vertex = 'ALO'

distances, previous_vertices = dijkstra(graph, start_vertex)

table_data = []
for vertex in labels:
    path = construct_path(previous_vertices, start_vertex, vertex)
    path_str = " -> ".join(path) if path else "N/A"
    table_data.append((vertex, distances[vertex], path_str))

distances_df = pd.DataFrame(table_data, columns=['Vértice', 'MINUTOS', 'Caminho'])
distances_df = distances_df.sort_values(by='Vértice').reset_index(drop=True)

print(distances_df)