import PyPDF2
import os
import sys

sys.stdout.reconfigure(encoding='utf-8')

def extract_pdf(filepath):
    try:
        with open(filepath, 'rb') as f:
            reader = PyPDF2.PdfReader(f)
            text = ""
            for i, page in enumerate(reader.pages):
                text += f"\n--- PAGE {i+1} ---\n"
                text += page.extract_text()
            return text
    except Exception as e:
        return f"ERROR: {e}"

base_dir = os.path.dirname(os.path.abspath(__file__))
output_file = os.path.join(base_dir, "extracted_text.txt")

# Dynamically list all PDFs in the directory
pdf_files = [f for f in os.listdir(base_dir) if f.lower().endswith('.pdf')]
# Sort them to keep it organized (resumos first, then exams)
pdf_files.sort(key=lambda x: (not x.startswith('BD'), x))

with open(output_file, 'w', encoding='utf-8') as out:
    for pdf_name in pdf_files:
        filepath = os.path.join(base_dir, pdf_name)
        out.write(f"\n{'='*80}\n")
        out.write(f"FILE: {pdf_name}\n")
        out.write(f"{'='*80}\n")
        text = extract_pdf(filepath)
        out.write(text)
        out.write("\n")

print(f"Done! Extracted {len(pdf_files)} PDF files to extracted_text.txt")

