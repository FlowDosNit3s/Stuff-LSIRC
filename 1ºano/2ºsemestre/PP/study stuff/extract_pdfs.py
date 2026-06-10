import os
from pdfminer.high_level import extract_text

base = os.path.dirname(os.path.abspath(__file__))
slides_dir = os.path.join(os.path.dirname(base), "Slides")
exames_dir = os.path.join(base, "EXAMES")
tp_dir = os.path.join(os.path.dirname(base), "Trabalhos Práticos", "25_26")
output_dir = os.path.join(base, "extracted")
os.makedirs(output_dir, exist_ok=True)

# Extract slides
for f in sorted(os.listdir(slides_dir)):
    if f.endswith('.pdf'):
        path = os.path.join(slides_dir, f)
        out = os.path.join(output_dir, f.replace('.pdf', '.txt'))
        print(f"Extracting {f}...")
        try:
            text = extract_text(path)
            with open(out, 'w', encoding='utf-8') as fout:
                fout.write(text)
        except Exception as e:
            print(f"  Error: {e}")

# Extract exams
for f in sorted(os.listdir(exames_dir)):
    if f.endswith('.pdf'):
        path = os.path.join(exames_dir, f)
        out = os.path.join(output_dir, "EXAM_" + f.replace('.pdf', '.txt'))
        print(f"Extracting {f}...")
        try:
            text = extract_text(path)
            with open(out, 'w', encoding='utf-8') as fout:
                fout.write(text)
        except Exception as e:
            print(f"  Error: {e}")

# Extract TP
for f in sorted(os.listdir(tp_dir)):
    if f.endswith('.pdf'):
        path = os.path.join(tp_dir, f)
        out = os.path.join(output_dir, "TP_" + f.replace('.pdf', '.txt'))
        print(f"Extracting {f}...")
        try:
            text = extract_text(path)
            with open(out, 'w', encoding='utf-8') as fout:
                fout.write(text)
        except Exception as e:
            print(f"  Error: {e}")

print("Done!")
