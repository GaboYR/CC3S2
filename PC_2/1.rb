def letras_faltantes(s)
    cont = Array.new(65, 0)
    s.each_char do |char|
      cont[char.ord - 'a'.ord] = 1
    end
    (0..25).each do |i|
      if cont[i] == 0
        puts "Falta #{('a'.ord + i).chr}"
      end
    end
    puts cont
  end
  cad = "the quick brown box jumps over a lazy dog"
  letras_faltantes(cad)